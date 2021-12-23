/**
 * Copyright © 2016-2021 The Thingsboard Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.application.service.security.system;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.tyuan.common.utils.JacksonUtil;
import org.tyuan.service.application.service.SysPermissionService;
import org.tyuan.service.application.service.SysUserService;
import org.tyuan.service.application.service.impl.SysUserServiceImpl;
import org.tyuan.service.dao.exception.DataValidationException;
import org.tyuan.service.data.model.SysUser;
import org.tyuan.service.data.model.SysUserCredentials;
import org.tyuan.service.data.security.SecuritySettings;
import org.tyuan.service.data.security.UserPasswordPolicy;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class DefaultSystemSecurityService implements SystemSecurityService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private SysUserService userService;

    @Resource
    private SystemSecurityService self;

    @Resource
    private SysPermissionService sysPermissionService;

    @Override
    public SecuritySettings getSecuritySettings(Long userId) {
        return null;
    }

    @Override
    public SecuritySettings saveSecuritySettings(Long userId, SecuritySettings securitySettings) {
        return null;
    }

    @Override
    public void validateUserCredentials(Long userId, SysUserCredentials userCredentials, String username, String password) throws AuthenticationException {
        if (!encoder.matches(password, userCredentials.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (!userCredentials.getEnabled()) {
            throw new DisabledException("User is not active");
        }

    }

    @Override
    public List<String> getAuthorities(Long uid) {
        List<String> byUserId = sysPermissionService.getByUserId(uid);
        return byUserId;
    }

    @Override
    public void validatePassword(Long userId, String password, SysUserCredentials userCredentials) throws DataValidationException {
        SecuritySettings securitySettings = self.getSecuritySettings(userId);
        UserPasswordPolicy passwordPolicy = securitySettings.getPasswordPolicy();

        List<Rule> passwordRules = new ArrayList<>();
        passwordRules.add(new LengthRule(passwordPolicy.getMinimumLength(), Integer.MAX_VALUE));
        if (isPositiveInteger(passwordPolicy.getMinimumUppercaseLetters())) {
            passwordRules.add(new CharacterRule(EnglishCharacterData.UpperCase, passwordPolicy.getMinimumUppercaseLetters()));
        }
        if (isPositiveInteger(passwordPolicy.getMinimumLowercaseLetters())) {
            passwordRules.add(new CharacterRule(EnglishCharacterData.LowerCase, passwordPolicy.getMinimumLowercaseLetters()));
        }
        if (isPositiveInteger(passwordPolicy.getMinimumDigits())) {
            passwordRules.add(new CharacterRule(EnglishCharacterData.Digit, passwordPolicy.getMinimumDigits()));
        }
        if (isPositiveInteger(passwordPolicy.getMinimumSpecialCharacters())) {
            passwordRules.add(new CharacterRule(EnglishCharacterData.Special, passwordPolicy.getMinimumSpecialCharacters()));
        }
        PasswordValidator validator = new PasswordValidator(passwordRules);
        PasswordData passwordData = new PasswordData(password);
        RuleResult result = validator.validate(passwordData);
        if (!result.isValid()) {
            String message = String.join("\n", validator.getMessages(result));
            throw new DataValidationException(message);
        }

        if (userCredentials != null && isPositiveInteger(passwordPolicy.getPasswordReuseFrequencyDays())) {
            long passwordReuseFrequencyTs = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(passwordPolicy.getPasswordReuseFrequencyDays());
            SysUser user = userService.getById(userId);
            try {
                JsonNode additionalInfo = JacksonUtil.toJsonNode(user.getAdditionalInfo());
                if (additionalInfo.has(SysUserServiceImpl.USER_PASSWORD_HISTORY)) {
                    JsonNode userPasswordHistoryJson = additionalInfo.get(SysUserServiceImpl.USER_PASSWORD_HISTORY);
                    Map<String, String> userPasswordHistoryMap = JacksonUtil.convertValue(userPasswordHistoryJson, new TypeReference<Map<String, String>>() {
                    });
                    for (Map.Entry<String, String> entry : userPasswordHistoryMap.entrySet()) {
                        if (encoder.matches(password, entry.getValue()) && Long.parseLong(entry.getKey()) > passwordReuseFrequencyTs) {
                            throw new DataValidationException("Password was already used for the last " + passwordPolicy.getPasswordReuseFrequencyDays() + " days");
                        }
                    }
                }
            } catch (Exception e) {
                throw new DataValidationException(e.getMessage());
            }

        }
    }

    private static boolean isPositiveInteger(Integer val) {
        return val != null && val.intValue() > 0;
    }
}

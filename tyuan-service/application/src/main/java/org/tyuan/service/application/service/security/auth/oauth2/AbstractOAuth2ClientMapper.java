/**
 * Copyright © 2016-2021 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.application.service.security.auth.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;
import org.tyuan.service.application.service.SysUserService;
import org.tyuan.service.application.service.security.model.SecurityUser;
import org.tyuan.service.application.service.security.model.UserPrincipal;
import org.tyuan.service.dao.model.SysUser;
import org.tyuan.service.data.oauth2.OAuth2MapperConfig;
import org.tyuan.service.data.oauth2.OAuth2Registration;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public abstract class AbstractOAuth2ClientMapper {
    private static final int DASHBOARDS_REQUEST_LIMIT = 10;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SysUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Value("${edges.enabled}")
    @Getter
    private boolean edgesEnabled;

    private final Lock userCreationLock = new ReentrantLock();

    protected SecurityUser getOrCreateSecurityUserFromOAuth2User(OAuth2User oauth2User, OAuth2Registration registration) {

        OAuth2MapperConfig config = registration.getMapperConfig();

        UserPrincipal principal = new UserPrincipal(UserPrincipal.Type.USER_NAME, oauth2User.getEmail());

        SysUser user = userService.findUserByEmail(TenantId.SYS_TENANT_ID, oauth2User.getEmail());

        if (user == null && !config.isAllowUserCreation()) {
            throw new UsernameNotFoundException("User not found: " + oauth2User.getEmail());
        }

        if (user == null) {
            userCreationLock.lock();
            try {
                user = userService.findUserByEmail(TenantId.SYS_TENANT_ID, oauth2User.getEmail());
                if (user == null) {
                    user = new User();
                    if (oauth2User.getCustomerId() == null && StringUtils.isEmpty(oauth2User.getCustomerName())) {
                        user.setAuthority(Authority.TENANT_ADMIN);
                    } else {
                        user.setAuthority(Authority.CUSTOMER_USER);
                    }
                    TenantId tenantId = oauth2User.getTenantId() != null ?
                            oauth2User.getTenantId() : getTenantId(oauth2User.getTenantName());
                    user.setTenantId(tenantId);
                    CustomerId customerId = oauth2User.getCustomerId() != null ?
                            oauth2User.getCustomerId() : getCustomerId(user.getTenantId(), oauth2User.getCustomerName());
                    user.setCustomerId(customerId);
                    user.setEmail(oauth2User.getEmail());
                    user.setFirstName(oauth2User.getFirstName());
                    user.setLastName(oauth2User.getLastName());

                    ObjectNode additionalInfo = objectMapper.createObjectNode();

                    if (!StringUtils.isEmpty(oauth2User.getDefaultDashboardName())) {
                        Optional<DashboardId> dashboardIdOpt =
                                user.getAuthority() == Authority.TENANT_ADMIN ?
                                        getDashboardId(tenantId, oauth2User.getDefaultDashboardName())
                                        : getDashboardId(tenantId, customerId, oauth2User.getDefaultDashboardName());
                        if (dashboardIdOpt.isPresent()) {
                            additionalInfo.put("defaultDashboardFullscreen", oauth2User.isAlwaysFullScreen());
                            additionalInfo.put("defaultDashboardId", dashboardIdOpt.get().getId().toString());
                        }
                    }

                    if (registration.getAdditionalInfo() != null &&
                            registration.getAdditionalInfo().has("providerName")) {
                        additionalInfo.put("authProviderName", registration.getAdditionalInfo().get("providerName").asText());
                    }

                    user.setAdditionalInfo(additionalInfo);

                    user = userService.saveUser(user);
                    if (config.isActivateUser()) {
                        UserCredentials userCredentials = userService.findUserCredentialsByUserId(user.getTenantId(), user.getId());
                        userService.activateUserCredentials(user.getTenantId(), userCredentials.getActivateToken(), passwordEncoder.encode(""));
                    }
                }
            } catch (Exception e) {
                log.error("Can't get or create security user from oauth2 user", e);
                throw new RuntimeException("Can't get or create security user from oauth2 user", e);
            } finally {
                userCreationLock.unlock();
            }
        }

        try {
            SecurityUser securityUser = new SecurityUser(user, true, principal);
            return (SecurityUser) new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities()).getPrincipal();
        } catch (Exception e) {
            log.error("Can't get or create security user from oauth2 user", e);
            throw new RuntimeException("Can't get or create security user from oauth2 user", e);
        }
    }
}

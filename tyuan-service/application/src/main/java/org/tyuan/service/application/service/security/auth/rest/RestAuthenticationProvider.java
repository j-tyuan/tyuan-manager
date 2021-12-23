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
package org.tyuan.service.application.service.security.auth.rest;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.tyuan.service.application.service.AuditLogService;
import org.tyuan.service.application.service.SysUserService;
import org.tyuan.service.application.service.security.model.SecurityUser;
import org.tyuan.service.application.service.security.model.UserPrincipal;
import org.tyuan.service.application.service.security.system.SystemSecurityService;
import org.tyuan.service.data.audit.ActionType;
import org.tyuan.service.data.model.SysUser;
import org.tyuan.service.data.model.SysUserCredentials;
import ua_parser.Client;


@Component
@Slf4j
public class RestAuthenticationProvider implements AuthenticationProvider {

    private final SystemSecurityService systemSecurityService;
    private final SysUserService userService;
    private final AuditLogService auditLogService;


    @Autowired
    public RestAuthenticationProvider(final SysUserService userService,
                                      final SystemSecurityService systemSecurityService,
                                      AuditLogService auditLogService) {
        this.userService = userService;
        this.systemSecurityService = systemSecurityService;
        this.auditLogService = auditLogService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserPrincipal)) {
            throw new BadCredentialsException("Authentication Failed. Bad user principal.");
        }

        UserPrincipal userPrincipal = (UserPrincipal) principal;
        String username = userPrincipal.getValue();
        String password = (String) authentication.getCredentials();
        return authenticateByUsernameAndPassword(authentication, userPrincipal, username, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private Authentication authenticateByUsernameAndPassword(Authentication authentication, UserPrincipal userPrincipal, String username, String password) {
        SysUser user = userService.getByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        try {

            try {
                SysUserCredentials userCredentials = userService.findUserCredentials(user.getId());
                if (userCredentials == null) {
                    throw new UsernameNotFoundException("User credentials not found");
                }

                systemSecurityService.validateUserCredentials(user.getId(), userCredentials, username, password);
            } catch (LockedException e) {
                logLoginAction(user, authentication, ActionType.LOCKOUT, null);
                throw e;
            }

            if (user.getAuthority() == null) {
                throw new InsufficientAuthenticationException("User has no authority assigned");
            }

            SecurityUser securityUser = new SecurityUser(user, true, userPrincipal,systemSecurityService);
            logLoginAction(user, authentication, ActionType.LOGIN, null);
            return new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
        } catch (Exception e) {
            logLoginAction(user, authentication, ActionType.LOGIN, e);
            throw e;
        }
    }

    private Authentication authenticateByPublicId(UserPrincipal userPrincipal, String publicId) {

        SecurityUser securityUser = new SecurityUser(new SysUser(), true, userPrincipal,systemSecurityService);

        return new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
    }

    private void logLoginAction(SysUser user, Authentication authentication, ActionType actionType, Exception e) {
        String clientAddress = "Unknown";
        String browser = "Unknown";
        String os = "Unknown";
        String device = "Unknown";
        if (authentication != null && authentication.getDetails() != null) {
            if (authentication.getDetails() instanceof RestAuthenticationDetails) {
                RestAuthenticationDetails details = (RestAuthenticationDetails) authentication.getDetails();
                clientAddress = details.getClientAddress();
                if (details.getUserAgent() != null) {
                    Client userAgent = details.getUserAgent();
                    if (userAgent.userAgent != null) {
                        browser = userAgent.userAgent.family;
                        if (userAgent.userAgent.major != null) {
                            browser += " " + userAgent.userAgent.major;
                            if (userAgent.userAgent.minor != null) {
                                browser += "." + userAgent.userAgent.minor;
                                if (userAgent.userAgent.patch != null) {
                                    browser += "." + userAgent.userAgent.patch;
                                }
                            }
                        }
                    }
                    if (userAgent.os != null) {
                        os = userAgent.os.family;
                        if (userAgent.os.major != null) {
                            os += " " + userAgent.os.major;
                            if (userAgent.os.minor != null) {
                                os += "." + userAgent.os.minor;
                                if (userAgent.os.patch != null) {
                                    os += "." + userAgent.os.patch;
                                    if (userAgent.os.patchMinor != null) {
                                        os += "." + userAgent.os.patchMinor;
                                    }
                                }
                            }
                        }
                    }
                    if (userAgent.device != null) {
                        device = userAgent.device.family;
                    }
                }
            }
        }
        log.info("User :{}   actionType:{} e:{} clientAddress:{} browser:{} os:{} device:{}", JSONObject.toJSONString(user), actionType, e, clientAddress, browser, os, device);
        auditLogService.logAction(user.getId(), user.getUserName(), actionType, e, clientAddress, browser, os, device);
    }
}

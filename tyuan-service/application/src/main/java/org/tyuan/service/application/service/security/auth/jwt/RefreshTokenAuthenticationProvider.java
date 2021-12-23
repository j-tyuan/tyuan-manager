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
package org.tyuan.service.application.service.security.auth.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.tyuan.service.application.service.SysUserService;
import org.tyuan.service.application.service.security.auth.RefreshAuthenticationToken;
import org.tyuan.service.application.service.security.auth.TokenOutdatingService;
import org.tyuan.service.application.service.security.model.SecurityUser;
import org.tyuan.service.application.service.security.model.UserPrincipal;
import org.tyuan.service.application.service.security.model.token.JwtTokenFactory;
import org.tyuan.service.application.service.security.model.token.RawAccessJwtToken;
import org.tyuan.service.application.service.security.system.SystemSecurityService;
import org.tyuan.service.data.model.SysUser;

@Component
@RequiredArgsConstructor
public class RefreshTokenAuthenticationProvider implements AuthenticationProvider {

    private final JwtTokenFactory tokenFactory;
    private final SysUserService userService;
    private final TokenOutdatingService tokenOutdatingService;
    private final SystemSecurityService systemSecurityService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
        SecurityUser unsafeUser = tokenFactory.parseRefreshToken(rawAccessToken);

        SecurityUser securityUser = authenticateByUserId(unsafeUser.getId());
        if (tokenOutdatingService.isOutdated(rawAccessToken, securityUser.getId())) {
            throw new CredentialsExpiredException("Token is outdated");
        }

        return new RefreshAuthenticationToken(securityUser);
    }

    private SecurityUser authenticateByUserId(Long userId) {
        SysUser user = userService.getById(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by refresh token");
        }

        if (user.getAuthority() == null) {
            throw new InsufficientAuthenticationException("User has no authority assigned");
        }

        UserPrincipal userPrincipal = new UserPrincipal(UserPrincipal.Type.USER_NAME, user.getUserAccount());

        SecurityUser securityUser = new SecurityUser(user, true, userPrincipal, systemSecurityService);

        return securityUser;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (RefreshAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

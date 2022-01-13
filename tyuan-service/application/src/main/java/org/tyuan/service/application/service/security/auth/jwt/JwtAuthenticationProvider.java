/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.tyuan.service.application.service.security.auth.JwtAuthenticationToken;
import org.tyuan.service.application.service.security.auth.TokenOutdatingService;
import org.tyuan.service.application.service.security.exception.JwtExpiredTokenException;
import org.tyuan.service.application.service.security.model.SecurityUser;
import org.tyuan.service.application.service.security.model.token.JwtTokenFactory;
import org.tyuan.service.application.service.security.model.token.RawAccessJwtToken;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtTokenFactory tokenFactory;
    private final TokenOutdatingService tokenOutdatingService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
        SecurityUser securityUser = tokenFactory.parseAccessJwtToken(rawAccessToken);

        if (tokenOutdatingService.isOutdated(rawAccessToken, securityUser.getId())) {
            throw new JwtExpiredTokenException("Token is outdated");
        }

        return new JwtAuthenticationToken(securityUser);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

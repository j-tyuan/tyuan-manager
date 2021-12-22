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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.tyuan.service.application.service.SysUserService;
import org.tyuan.service.application.service.security.model.SecurityUser;
import org.tyuan.service.data.oauth2.OAuth2Registration;

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

    private final Lock userCreationLock = new ReentrantLock();

    protected SecurityUser getOrCreateSecurityUserFromOAuth2User(OAuth2User oauth2User, OAuth2Registration registration) {

        throw new RuntimeException(" --  没实现");
    }
}

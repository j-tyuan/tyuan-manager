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

import org.springframework.security.core.AuthenticationException;
import org.tyuan.service.dao.exception.DataValidationException;
import org.tyuan.service.data.model.SysUserCredentials;
import org.tyuan.service.data.security.SecuritySettings;

public interface SystemSecurityService {


    SecuritySettings getSecuritySettings(Long userId);

    SecuritySettings saveSecuritySettings(Long userId, SecuritySettings securitySettings);

    void validateUserCredentials(Long userId, SysUserCredentials userCredentials, String username, String password) throws AuthenticationException;

    void validatePassword(Long userId, String password, SysUserCredentials userCredentials) throws DataValidationException;
}

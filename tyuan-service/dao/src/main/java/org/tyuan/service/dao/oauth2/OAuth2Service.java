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
package org.tyuan.service.dao.oauth2;


import org.tyuan.service.data.model.OAuth2Registration;
import org.tyuan.service.data.oauth2.OAuth2ClientInfo;
import org.tyuan.service.data.oauth2.OAuth2Info;
import org.tyuan.service.data.oauth2.PlatformType;

import java.util.List;

public interface OAuth2Service {
    List<OAuth2ClientInfo> getOAuth2Clients(String domainScheme, String domainName, String pkgName, PlatformType platformType);

    void saveOAuth2Info(OAuth2Info oauth2Info);

    OAuth2Info findOAuth2Info();

    OAuth2Registration findRegistration(String id);

    String findAppSecret(String registrationId, String pkgName);
}

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
package org.tyuan.service.dao.oauth2.impl;


import org.tyuan.service.data.model.OAuth2Registration;
import org.tyuan.service.data.oauth2.*;

import java.util.List;

public class OAuth2Utils {
    public static final String OAUTH2_AUTHORIZATION_PATH_TEMPLATE = "/oauth2/authorization/%s";

    public static OAuth2ClientInfo toClientInfo(OAuth2Registration registration) {
        OAuth2ClientInfo client = new OAuth2ClientInfo();
        client.setName(registration.getLoginButtonLabel());
        client.setUrl(String.format(OAUTH2_AUTHORIZATION_PATH_TEMPLATE, registration.getId()));
        client.setIcon(registration.getLoginButtonIcon());
        return client;
    }

    public static OAuth2ParamsInfo toOAuth2ParamsInfo(List<OAuth2Registration> registrations, List<OAuth2Domain> domains, List<OAuth2Mobile> mobiles) {
        throw new RuntimeException("待实现");
    }

    public static OAuth2RegistrationInfo toOAuth2RegistrationInfo(OAuth2Registration registration) {
        throw new RuntimeException("待实现");
    }

    public static OAuth2DomainInfo toOAuth2DomainInfo(OAuth2Domain domain) {
        return OAuth2DomainInfo.builder()
                .name(domain.getDomainName())
                .scheme(domain.getDomainScheme())
                .build();
    }

    public static OAuth2MobileInfo toOAuth2MobileInfo(OAuth2Mobile mobile) {
        return OAuth2MobileInfo.builder()
                .pkgName(mobile.getPkgName())
                .appSecret(mobile.getAppSecret())
                .build();
    }

    public static OAuth2Params infoToOAuth2Params(OAuth2Info oauth2Info) {
        throw new RuntimeException("待实现");
    }

    public static OAuth2Registration toOAuth2Registration(String oauth2ParamsId, OAuth2RegistrationInfo registrationInfo) {
        throw new RuntimeException("待实现");
    }

    public static OAuth2Domain toOAuth2Domain(String oauth2ParamsId, OAuth2DomainInfo domainInfo) {
        throw new RuntimeException("待实现");
    }

    public static OAuth2Mobile toOAuth2Mobile(String oauth2ParamsId, OAuth2MobileInfo mobileInfo) {
        throw new RuntimeException("待实现");
    }
}

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

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.tyuan.service.dao.exception.DataValidationException;
import org.tyuan.service.dao.mapper.OAuth2DomainMapper;
import org.tyuan.service.dao.mapper.OAuth2MobileMapper;
import org.tyuan.service.dao.mapper.OAuth2ParamsMapper;
import org.tyuan.service.dao.mapper.OAuth2RegistrationMapper;
import org.tyuan.service.dao.oauth2.OAuth2Service;
import org.tyuan.service.data.model.OAuth2Registration;
import org.tyuan.service.data.oauth2.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Slf4j
@Service
public class OAuth2ServiceImpl implements OAuth2Service {
    public static final String INCORRECT_TENANT_ID = "Incorrect tenantId ";
    public static final String INCORRECT_CLIENT_REGISTRATION_ID = "Incorrect clientRegistrationId ";
    public static final String INCORRECT_DOMAIN_NAME = "Incorrect domainName ";
    public static final String INCORRECT_DOMAIN_SCHEME = "Incorrect domainScheme ";

    @Resource
    private OAuth2ParamsMapper oauth2ParamsMapper;

    @Resource
    private OAuth2RegistrationMapper registrationRepository;

    @Resource
    private OAuth2DomainMapper oauth2DomainMapper;

    @Resource
    private OAuth2MobileMapper oauth2MobileMapper;

    @Override
    public List<OAuth2ClientInfo> getOAuth2Clients(String domainSchemeStr, String domainName, String pkgName, PlatformType platformType) {
        log.trace("Executing getOAuth2Clients [{}://{}] pkgName=[{}] platformType=[{}]", domainSchemeStr, domainName, pkgName, platformType);
        throw new RuntimeException("待实现");
    }

    @Override
    @Transactional
    public void saveOAuth2Info(OAuth2Info oauth2Info) {
        throw new RuntimeException("待实现");
    }

    @Override
    public OAuth2Info findOAuth2Info() {
        throw new RuntimeException("待实现");
    }

    @Override
    public OAuth2Registration findRegistration(String id) {
        throw new RuntimeException("待实现");
    }

    @Override
    public String findAppSecret(String id, String pkgName) {
        throw new RuntimeException("待实现");
    }


    private final Consumer<OAuth2Info> oauth2InfoValidator = oauth2Info -> {
        if (oauth2Info == null
                || oauth2Info.getOauth2ParamsInfos() == null) {
            throw new DataValidationException("OAuth2 param infos should be specified!");
        }
        for (OAuth2ParamsInfo oauth2Params : oauth2Info.getOauth2ParamsInfos()) {
            if (oauth2Params.getDomainInfos() == null
                    || oauth2Params.getDomainInfos().isEmpty()) {
                throw new DataValidationException("List of domain configuration should be specified!");
            }
            for (OAuth2DomainInfo domainInfo : oauth2Params.getDomainInfos()) {
                if (StringUtils.isEmpty(domainInfo.getName())) {
                    throw new DataValidationException("Domain name should be specified!");
                }
                if (domainInfo.getScheme() == null) {
                    throw new DataValidationException("Domain scheme should be specified!");
                }
            }
            oauth2Params.getDomainInfos().stream()
                    .collect(Collectors.groupingBy(OAuth2DomainInfo::getName))
                    .forEach((domainName, domainInfos) -> {
                        if (domainInfos.size() > 1 && domainInfos.stream().anyMatch(domainInfo -> domainInfo.getScheme() == SchemeType.MIXED)) {
                            throw new DataValidationException("MIXED scheme type shouldn't be combined with another scheme type!");
                        }
                        domainInfos.stream()
                                .collect(Collectors.groupingBy(OAuth2DomainInfo::getScheme))
                                .forEach((schemeType, domainInfosBySchemeType) -> {
                                    if (domainInfosBySchemeType.size() > 1) {
                                        throw new DataValidationException("Domain name and protocol must be unique within OAuth2 parameters!");
                                    }
                                });
                    });
            if (oauth2Params.getMobileInfos() != null) {
                for (OAuth2MobileInfo mobileInfo : oauth2Params.getMobileInfos()) {
                    if (StringUtils.isEmpty(mobileInfo.getPkgName())) {
                        throw new DataValidationException("Package should be specified!");
                    }
                    if (StringUtils.isEmpty(mobileInfo.getAppSecret())) {
                        throw new DataValidationException("Application secret should be specified!");
                    }
                    if (mobileInfo.getAppSecret().length() < 16) {
                        throw new DataValidationException("Application secret should be at least 16 characters!");
                    }
                }
                oauth2Params.getMobileInfos().stream()
                        .collect(Collectors.groupingBy(OAuth2MobileInfo::getPkgName))
                        .forEach((pkgName, mobileInfos) -> {
                            if (mobileInfos.size() > 1) {
                                throw new DataValidationException("Mobile app package name must be unique within OAuth2 parameters!");
                            }
                        });
            }
            if (oauth2Params.getClientRegistrations() == null || oauth2Params.getClientRegistrations().isEmpty()) {
                throw new DataValidationException("Client registrations should be specified!");
            }
            for (OAuth2RegistrationInfo clientRegistration : oauth2Params.getClientRegistrations()) {
                if (StringUtils.isEmpty(clientRegistration.getClientId())) {
                    throw new DataValidationException("Client ID should be specified!");
                }
                if (StringUtils.isEmpty(clientRegistration.getClientSecret())) {
                    throw new DataValidationException("Client secret should be specified!");
                }
                if (StringUtils.isEmpty(clientRegistration.getAuthorizationUri())) {
                    throw new DataValidationException("Authorization uri should be specified!");
                }
                if (StringUtils.isEmpty(clientRegistration.getAccessTokenUri())) {
                    throw new DataValidationException("Token uri should be specified!");
                }
                if (CollectionUtils.isEmpty(clientRegistration.getScope())) {
                    throw new DataValidationException("Scope should be specified!");
                }
                if (StringUtils.isEmpty(clientRegistration.getUserNameAttributeName())) {
                    throw new DataValidationException("User name attribute name should be specified!");
                }
                if (StringUtils.isEmpty(clientRegistration.getClientAuthenticationMethod())) {
                    throw new DataValidationException("Client authentication method should be specified!");
                }
                if (StringUtils.isEmpty(clientRegistration.getLoginButtonLabel())) {
                    throw new DataValidationException("Login button label should be specified!");
                }
                OAuth2MapperConfig mapperConfig = clientRegistration.getMapperConfig();
                if (mapperConfig == null) {
                    throw new DataValidationException("Mapper config should be specified!");
                }
                if (mapperConfig.getType() == null) {
                    throw new DataValidationException("Mapper config type should be specified!");
                }
                if (mapperConfig.getType() == MapperType.BASIC) {
                    OAuth2BasicMapperConfig basicConfig = mapperConfig.getBasic();
                    if (basicConfig == null) {
                        throw new DataValidationException("Basic config should be specified!");
                    }
                    if (StringUtils.isEmpty(basicConfig.getEmailAttributeKey())) {
                        throw new DataValidationException("Email attribute key should be specified!");
                    }
                    if (basicConfig.getTenantNameStrategy() == null) {
                        throw new DataValidationException("Tenant name strategy should be specified!");
                    }
                    if (basicConfig.getTenantNameStrategy() == TenantNameStrategyType.CUSTOM
                            && StringUtils.isEmpty(basicConfig.getTenantNamePattern())) {
                        throw new DataValidationException("Tenant name pattern should be specified!");
                    }
                }
                if (mapperConfig.getType() == MapperType.GITHUB) {
                    OAuth2BasicMapperConfig basicConfig = mapperConfig.getBasic();
                    if (basicConfig == null) {
                        throw new DataValidationException("Basic config should be specified!");
                    }
                    if (!StringUtils.isEmpty(basicConfig.getEmailAttributeKey())) {
                        throw new DataValidationException("Email attribute key cannot be configured for GITHUB mapper type!");
                    }
                    if (basicConfig.getTenantNameStrategy() == null) {
                        throw new DataValidationException("Tenant name strategy should be specified!");
                    }
                    if (basicConfig.getTenantNameStrategy() == TenantNameStrategyType.CUSTOM
                            && StringUtils.isEmpty(basicConfig.getTenantNamePattern())) {
                        throw new DataValidationException("Tenant name pattern should be specified!");
                    }
                }
                if (mapperConfig.getType() == MapperType.CUSTOM) {
                    OAuth2CustomMapperConfig customConfig = mapperConfig.getCustom();
                    if (customConfig == null) {
                        throw new DataValidationException("Custom config should be specified!");
                    }
                    if (StringUtils.isEmpty(customConfig.getUrl())) {
                        throw new DataValidationException("Custom mapper URL should be specified!");
                    }
                }
            }
        }
    };
}

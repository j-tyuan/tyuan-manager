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
import org.springframework.stereotype.Service;
import org.tyuan.service.dao.oauth2.OAuth2ConfigTemplateService;
import org.tyuan.service.data.oauth2.OAuth2ClientRegistrationTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OAuth2ConfigTemplateServiceImpl implements OAuth2ConfigTemplateService {
    public static final String INCORRECT_CLIENT_REGISTRATION_TEMPLATE_ID = "Incorrect clientRegistrationTemplateId ";
    public static final String INCORRECT_CLIENT_REGISTRATION_PROVIDER_ID = "Incorrect clientRegistrationProviderId ";

    //@Autowired
    //private OAuth2ClientRegistrationTemplateDao clientRegistrationTemplateDao;

    @Override
    public OAuth2ClientRegistrationTemplate saveClientRegistrationTemplate(OAuth2ClientRegistrationTemplate clientRegistrationTemplate) {
        throw new RuntimeException("待实现");
    }

    @Override
    public Optional<OAuth2ClientRegistrationTemplate> findClientRegistrationTemplateByProviderId(String providerId) {
        throw new RuntimeException("待实现");
    }

    @Override
    public OAuth2ClientRegistrationTemplate findClientRegistrationTemplateById(String templateId) {
        throw new RuntimeException("待实现");
    }

    @Override
    public List<OAuth2ClientRegistrationTemplate> findAllClientRegistrationTemplates() {
        throw new RuntimeException("待实现");
    }

    @Override
    public void deleteClientRegistrationTemplateById(String templateId) {
        throw new RuntimeException("待实现");
    }

/*    private final DataValidator<OAuth2ClientRegistrationTemplate> clientRegistrationTemplateValidator =
            new DataValidator<OAuth2ClientRegistrationTemplate>() {

                @Override
                protected void validateCreate(TenantId tenantId, OAuth2ClientRegistrationTemplate clientRegistrationTemplate) {
                }

                @Override
                protected void validateUpdate(TenantId tenantId, OAuth2ClientRegistrationTemplate clientRegistrationTemplate) {
                }

                @Override
                protected void validateDataImpl(TenantId tenantId, OAuth2ClientRegistrationTemplate clientRegistrationTemplate) {
                    if (StringUtils.isEmpty(clientRegistrationTemplate.getProviderId())) {
                        throw new DataValidationException("Provider ID should be specified!");
                    }
                    if (clientRegistrationTemplate.getMapperConfig() == null) {
                        throw new DataValidationException("Mapper config should be specified!");
                    }
                    if (clientRegistrationTemplate.getMapperConfig().getType() == null) {
                        throw new DataValidationException("Mapper type should be specified!");
                    }
                    if (clientRegistrationTemplate.getMapperConfig().getBasic() == null) {
                        throw new DataValidationException("Basic mapper config should be specified!");
                    }
                }
            };*/
}

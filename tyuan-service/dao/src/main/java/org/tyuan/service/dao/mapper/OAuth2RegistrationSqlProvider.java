package org.tyuan.service.dao.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.tyuan.service.data.model.OAuth2Registration;
import org.tyuan.service.data.model.OAuth2RegistrationExample.Criteria;
import org.tyuan.service.data.model.OAuth2RegistrationExample.Criterion;
import org.tyuan.service.data.model.OAuth2RegistrationExample;

public class OAuth2RegistrationSqlProvider {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_registration
     *
     * @mbg.generated
     */
    public String countByExample(OAuth2RegistrationExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("oauth2_registration");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_registration
     *
     * @mbg.generated
     */
    public String deleteByExample(OAuth2RegistrationExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("oauth2_registration");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_registration
     *
     * @mbg.generated
     */
    public String insertSelective(OAuth2Registration record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("oauth2_registration");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getClientId() != null) {
            sql.VALUES("client_id", "#{clientId,jdbcType=VARCHAR}");
        }
        
        if (record.getClientSecret() != null) {
            sql.VALUES("client_secret", "#{clientSecret,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorizationUri() != null) {
            sql.VALUES("authorization_uri", "#{authorizationUri,jdbcType=VARCHAR}");
        }
        
        if (record.getTokenUri() != null) {
            sql.VALUES("token_uri", "#{tokenUri,jdbcType=VARCHAR}");
        }
        
        if (record.getScope() != null) {
            sql.VALUES("scope", "#{scope,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatforms() != null) {
            sql.VALUES("platforms", "#{platforms,jdbcType=VARCHAR}");
        }
        
        if (record.getUserInfoUri() != null) {
            sql.VALUES("user_info_uri", "#{userInfoUri,jdbcType=VARCHAR}");
        }
        
        if (record.getUserNameAttributeName() != null) {
            sql.VALUES("user_name_attribute_name", "#{userNameAttributeName,jdbcType=VARCHAR}");
        }
        
        if (record.getJwkSetUri() != null) {
            sql.VALUES("jwk_set_uri", "#{jwkSetUri,jdbcType=VARCHAR}");
        }
        
        if (record.getClientAuthenticationMethod() != null) {
            sql.VALUES("client_authentication_method", "#{clientAuthenticationMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginButtonLabel() != null) {
            sql.VALUES("login_button_label", "#{loginButtonLabel,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginButtonIcon() != null) {
            sql.VALUES("login_button_icon", "#{loginButtonIcon,jdbcType=VARCHAR}");
        }
        
        if (record.getAllowUserCreation() != null) {
            sql.VALUES("allow_user_creation", "#{allowUserCreation,jdbcType=BIT}");
        }
        
        if (record.getActivateUser() != null) {
            sql.VALUES("activate_user", "#{activateUser,jdbcType=BIT}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicEmailAttributeKey() != null) {
            sql.VALUES("basic_email_attribute_key", "#{basicEmailAttributeKey,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicFirstNameAttributeKey() != null) {
            sql.VALUES("basic_first_name_attribute_key", "#{basicFirstNameAttributeKey,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicLastNameAttributeKey() != null) {
            sql.VALUES("basic_last_name_attribute_key", "#{basicLastNameAttributeKey,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicTenantNameStrategy() != null) {
            sql.VALUES("basic_tenant_name_strategy", "#{basicTenantNameStrategy,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicTenantNamePattern() != null) {
            sql.VALUES("basic_tenant_name_pattern", "#{basicTenantNamePattern,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicCustomerNamePattern() != null) {
            sql.VALUES("basic_customer_name_pattern", "#{basicCustomerNamePattern,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicDefaultDashboardName() != null) {
            sql.VALUES("basic_default_dashboard_name", "#{basicDefaultDashboardName,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicAlwaysFullScreen() != null) {
            sql.VALUES("basic_always_full_screen", "#{basicAlwaysFullScreen,jdbcType=BIT}");
        }
        
        if (record.getCustomUrl() != null) {
            sql.VALUES("custom_url", "#{customUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomUsername() != null) {
            sql.VALUES("custom_username", "#{customUsername,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomPassword() != null) {
            sql.VALUES("custom_password", "#{customPassword,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomSendToken() != null) {
            sql.VALUES("custom_send_token", "#{customSendToken,jdbcType=BIT}");
        }
        
        if (record.getAdditionalInfo() != null) {
            sql.VALUES("additional_info", "#{additionalInfo,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_registration
     *
     * @mbg.generated
     */
    public String selectByExampleWithBLOBs(OAuth2RegistrationExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.SELECT("client_id");
        sql.SELECT("client_secret");
        sql.SELECT("authorization_uri");
        sql.SELECT("token_uri");
        sql.SELECT("scope");
        sql.SELECT("platforms");
        sql.SELECT("user_info_uri");
        sql.SELECT("user_name_attribute_name");
        sql.SELECT("jwk_set_uri");
        sql.SELECT("client_authentication_method");
        sql.SELECT("login_button_label");
        sql.SELECT("login_button_icon");
        sql.SELECT("allow_user_creation");
        sql.SELECT("activate_user");
        sql.SELECT("type");
        sql.SELECT("basic_email_attribute_key");
        sql.SELECT("basic_first_name_attribute_key");
        sql.SELECT("basic_last_name_attribute_key");
        sql.SELECT("basic_tenant_name_strategy");
        sql.SELECT("basic_tenant_name_pattern");
        sql.SELECT("basic_customer_name_pattern");
        sql.SELECT("basic_default_dashboard_name");
        sql.SELECT("basic_always_full_screen");
        sql.SELECT("custom_url");
        sql.SELECT("custom_username");
        sql.SELECT("custom_password");
        sql.SELECT("custom_send_token");
        sql.SELECT("additional_info");
        sql.FROM("oauth2_registration");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_registration
     *
     * @mbg.generated
     */
    public String selectByExample(OAuth2RegistrationExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.SELECT("client_id");
        sql.SELECT("client_secret");
        sql.SELECT("authorization_uri");
        sql.SELECT("token_uri");
        sql.SELECT("scope");
        sql.SELECT("platforms");
        sql.SELECT("user_info_uri");
        sql.SELECT("user_name_attribute_name");
        sql.SELECT("jwk_set_uri");
        sql.SELECT("client_authentication_method");
        sql.SELECT("login_button_label");
        sql.SELECT("login_button_icon");
        sql.SELECT("allow_user_creation");
        sql.SELECT("activate_user");
        sql.SELECT("type");
        sql.SELECT("basic_email_attribute_key");
        sql.SELECT("basic_first_name_attribute_key");
        sql.SELECT("basic_last_name_attribute_key");
        sql.SELECT("basic_tenant_name_strategy");
        sql.SELECT("basic_tenant_name_pattern");
        sql.SELECT("basic_customer_name_pattern");
        sql.SELECT("basic_default_dashboard_name");
        sql.SELECT("basic_always_full_screen");
        sql.SELECT("custom_url");
        sql.SELECT("custom_username");
        sql.SELECT("custom_password");
        sql.SELECT("custom_send_token");
        sql.FROM("oauth2_registration");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_registration
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        OAuth2Registration record = (OAuth2Registration) parameter.get("record");
        OAuth2RegistrationExample example = (OAuth2RegistrationExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("oauth2_registration");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getClientId() != null) {
            sql.SET("client_id = #{record.clientId,jdbcType=VARCHAR}");
        }
        
        if (record.getClientSecret() != null) {
            sql.SET("client_secret = #{record.clientSecret,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorizationUri() != null) {
            sql.SET("authorization_uri = #{record.authorizationUri,jdbcType=VARCHAR}");
        }
        
        if (record.getTokenUri() != null) {
            sql.SET("token_uri = #{record.tokenUri,jdbcType=VARCHAR}");
        }
        
        if (record.getScope() != null) {
            sql.SET("scope = #{record.scope,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatforms() != null) {
            sql.SET("platforms = #{record.platforms,jdbcType=VARCHAR}");
        }
        
        if (record.getUserInfoUri() != null) {
            sql.SET("user_info_uri = #{record.userInfoUri,jdbcType=VARCHAR}");
        }
        
        if (record.getUserNameAttributeName() != null) {
            sql.SET("user_name_attribute_name = #{record.userNameAttributeName,jdbcType=VARCHAR}");
        }
        
        if (record.getJwkSetUri() != null) {
            sql.SET("jwk_set_uri = #{record.jwkSetUri,jdbcType=VARCHAR}");
        }
        
        if (record.getClientAuthenticationMethod() != null) {
            sql.SET("client_authentication_method = #{record.clientAuthenticationMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginButtonLabel() != null) {
            sql.SET("login_button_label = #{record.loginButtonLabel,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginButtonIcon() != null) {
            sql.SET("login_button_icon = #{record.loginButtonIcon,jdbcType=VARCHAR}");
        }
        
        if (record.getAllowUserCreation() != null) {
            sql.SET("allow_user_creation = #{record.allowUserCreation,jdbcType=BIT}");
        }
        
        if (record.getActivateUser() != null) {
            sql.SET("activate_user = #{record.activateUser,jdbcType=BIT}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{record.type,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicEmailAttributeKey() != null) {
            sql.SET("basic_email_attribute_key = #{record.basicEmailAttributeKey,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicFirstNameAttributeKey() != null) {
            sql.SET("basic_first_name_attribute_key = #{record.basicFirstNameAttributeKey,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicLastNameAttributeKey() != null) {
            sql.SET("basic_last_name_attribute_key = #{record.basicLastNameAttributeKey,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicTenantNameStrategy() != null) {
            sql.SET("basic_tenant_name_strategy = #{record.basicTenantNameStrategy,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicTenantNamePattern() != null) {
            sql.SET("basic_tenant_name_pattern = #{record.basicTenantNamePattern,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicCustomerNamePattern() != null) {
            sql.SET("basic_customer_name_pattern = #{record.basicCustomerNamePattern,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicDefaultDashboardName() != null) {
            sql.SET("basic_default_dashboard_name = #{record.basicDefaultDashboardName,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicAlwaysFullScreen() != null) {
            sql.SET("basic_always_full_screen = #{record.basicAlwaysFullScreen,jdbcType=BIT}");
        }
        
        if (record.getCustomUrl() != null) {
            sql.SET("custom_url = #{record.customUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomUsername() != null) {
            sql.SET("custom_username = #{record.customUsername,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomPassword() != null) {
            sql.SET("custom_password = #{record.customPassword,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomSendToken() != null) {
            sql.SET("custom_send_token = #{record.customSendToken,jdbcType=BIT}");
        }
        
        if (record.getAdditionalInfo() != null) {
            sql.SET("additional_info = #{record.additionalInfo,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_registration
     *
     * @mbg.generated
     */
    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("oauth2_registration");
        
        sql.SET("id = #{record.id,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("client_id = #{record.clientId,jdbcType=VARCHAR}");
        sql.SET("client_secret = #{record.clientSecret,jdbcType=VARCHAR}");
        sql.SET("authorization_uri = #{record.authorizationUri,jdbcType=VARCHAR}");
        sql.SET("token_uri = #{record.tokenUri,jdbcType=VARCHAR}");
        sql.SET("scope = #{record.scope,jdbcType=VARCHAR}");
        sql.SET("platforms = #{record.platforms,jdbcType=VARCHAR}");
        sql.SET("user_info_uri = #{record.userInfoUri,jdbcType=VARCHAR}");
        sql.SET("user_name_attribute_name = #{record.userNameAttributeName,jdbcType=VARCHAR}");
        sql.SET("jwk_set_uri = #{record.jwkSetUri,jdbcType=VARCHAR}");
        sql.SET("client_authentication_method = #{record.clientAuthenticationMethod,jdbcType=VARCHAR}");
        sql.SET("login_button_label = #{record.loginButtonLabel,jdbcType=VARCHAR}");
        sql.SET("login_button_icon = #{record.loginButtonIcon,jdbcType=VARCHAR}");
        sql.SET("allow_user_creation = #{record.allowUserCreation,jdbcType=BIT}");
        sql.SET("activate_user = #{record.activateUser,jdbcType=BIT}");
        sql.SET("type = #{record.type,jdbcType=VARCHAR}");
        sql.SET("basic_email_attribute_key = #{record.basicEmailAttributeKey,jdbcType=VARCHAR}");
        sql.SET("basic_first_name_attribute_key = #{record.basicFirstNameAttributeKey,jdbcType=VARCHAR}");
        sql.SET("basic_last_name_attribute_key = #{record.basicLastNameAttributeKey,jdbcType=VARCHAR}");
        sql.SET("basic_tenant_name_strategy = #{record.basicTenantNameStrategy,jdbcType=VARCHAR}");
        sql.SET("basic_tenant_name_pattern = #{record.basicTenantNamePattern,jdbcType=VARCHAR}");
        sql.SET("basic_customer_name_pattern = #{record.basicCustomerNamePattern,jdbcType=VARCHAR}");
        sql.SET("basic_default_dashboard_name = #{record.basicDefaultDashboardName,jdbcType=VARCHAR}");
        sql.SET("basic_always_full_screen = #{record.basicAlwaysFullScreen,jdbcType=BIT}");
        sql.SET("custom_url = #{record.customUrl,jdbcType=VARCHAR}");
        sql.SET("custom_username = #{record.customUsername,jdbcType=VARCHAR}");
        sql.SET("custom_password = #{record.customPassword,jdbcType=VARCHAR}");
        sql.SET("custom_send_token = #{record.customSendToken,jdbcType=BIT}");
        sql.SET("additional_info = #{record.additionalInfo,jdbcType=LONGVARCHAR}");
        
        OAuth2RegistrationExample example = (OAuth2RegistrationExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_registration
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("oauth2_registration");
        
        sql.SET("id = #{record.id,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("client_id = #{record.clientId,jdbcType=VARCHAR}");
        sql.SET("client_secret = #{record.clientSecret,jdbcType=VARCHAR}");
        sql.SET("authorization_uri = #{record.authorizationUri,jdbcType=VARCHAR}");
        sql.SET("token_uri = #{record.tokenUri,jdbcType=VARCHAR}");
        sql.SET("scope = #{record.scope,jdbcType=VARCHAR}");
        sql.SET("platforms = #{record.platforms,jdbcType=VARCHAR}");
        sql.SET("user_info_uri = #{record.userInfoUri,jdbcType=VARCHAR}");
        sql.SET("user_name_attribute_name = #{record.userNameAttributeName,jdbcType=VARCHAR}");
        sql.SET("jwk_set_uri = #{record.jwkSetUri,jdbcType=VARCHAR}");
        sql.SET("client_authentication_method = #{record.clientAuthenticationMethod,jdbcType=VARCHAR}");
        sql.SET("login_button_label = #{record.loginButtonLabel,jdbcType=VARCHAR}");
        sql.SET("login_button_icon = #{record.loginButtonIcon,jdbcType=VARCHAR}");
        sql.SET("allow_user_creation = #{record.allowUserCreation,jdbcType=BIT}");
        sql.SET("activate_user = #{record.activateUser,jdbcType=BIT}");
        sql.SET("type = #{record.type,jdbcType=VARCHAR}");
        sql.SET("basic_email_attribute_key = #{record.basicEmailAttributeKey,jdbcType=VARCHAR}");
        sql.SET("basic_first_name_attribute_key = #{record.basicFirstNameAttributeKey,jdbcType=VARCHAR}");
        sql.SET("basic_last_name_attribute_key = #{record.basicLastNameAttributeKey,jdbcType=VARCHAR}");
        sql.SET("basic_tenant_name_strategy = #{record.basicTenantNameStrategy,jdbcType=VARCHAR}");
        sql.SET("basic_tenant_name_pattern = #{record.basicTenantNamePattern,jdbcType=VARCHAR}");
        sql.SET("basic_customer_name_pattern = #{record.basicCustomerNamePattern,jdbcType=VARCHAR}");
        sql.SET("basic_default_dashboard_name = #{record.basicDefaultDashboardName,jdbcType=VARCHAR}");
        sql.SET("basic_always_full_screen = #{record.basicAlwaysFullScreen,jdbcType=BIT}");
        sql.SET("custom_url = #{record.customUrl,jdbcType=VARCHAR}");
        sql.SET("custom_username = #{record.customUsername,jdbcType=VARCHAR}");
        sql.SET("custom_password = #{record.customPassword,jdbcType=VARCHAR}");
        sql.SET("custom_send_token = #{record.customSendToken,jdbcType=BIT}");
        
        OAuth2RegistrationExample example = (OAuth2RegistrationExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_registration
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(OAuth2Registration record) {
        SQL sql = new SQL();
        sql.UPDATE("oauth2_registration");
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getClientId() != null) {
            sql.SET("client_id = #{clientId,jdbcType=VARCHAR}");
        }
        
        if (record.getClientSecret() != null) {
            sql.SET("client_secret = #{clientSecret,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorizationUri() != null) {
            sql.SET("authorization_uri = #{authorizationUri,jdbcType=VARCHAR}");
        }
        
        if (record.getTokenUri() != null) {
            sql.SET("token_uri = #{tokenUri,jdbcType=VARCHAR}");
        }
        
        if (record.getScope() != null) {
            sql.SET("scope = #{scope,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatforms() != null) {
            sql.SET("platforms = #{platforms,jdbcType=VARCHAR}");
        }
        
        if (record.getUserInfoUri() != null) {
            sql.SET("user_info_uri = #{userInfoUri,jdbcType=VARCHAR}");
        }
        
        if (record.getUserNameAttributeName() != null) {
            sql.SET("user_name_attribute_name = #{userNameAttributeName,jdbcType=VARCHAR}");
        }
        
        if (record.getJwkSetUri() != null) {
            sql.SET("jwk_set_uri = #{jwkSetUri,jdbcType=VARCHAR}");
        }
        
        if (record.getClientAuthenticationMethod() != null) {
            sql.SET("client_authentication_method = #{clientAuthenticationMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginButtonLabel() != null) {
            sql.SET("login_button_label = #{loginButtonLabel,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginButtonIcon() != null) {
            sql.SET("login_button_icon = #{loginButtonIcon,jdbcType=VARCHAR}");
        }
        
        if (record.getAllowUserCreation() != null) {
            sql.SET("allow_user_creation = #{allowUserCreation,jdbcType=BIT}");
        }
        
        if (record.getActivateUser() != null) {
            sql.SET("activate_user = #{activateUser,jdbcType=BIT}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicEmailAttributeKey() != null) {
            sql.SET("basic_email_attribute_key = #{basicEmailAttributeKey,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicFirstNameAttributeKey() != null) {
            sql.SET("basic_first_name_attribute_key = #{basicFirstNameAttributeKey,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicLastNameAttributeKey() != null) {
            sql.SET("basic_last_name_attribute_key = #{basicLastNameAttributeKey,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicTenantNameStrategy() != null) {
            sql.SET("basic_tenant_name_strategy = #{basicTenantNameStrategy,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicTenantNamePattern() != null) {
            sql.SET("basic_tenant_name_pattern = #{basicTenantNamePattern,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicCustomerNamePattern() != null) {
            sql.SET("basic_customer_name_pattern = #{basicCustomerNamePattern,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicDefaultDashboardName() != null) {
            sql.SET("basic_default_dashboard_name = #{basicDefaultDashboardName,jdbcType=VARCHAR}");
        }
        
        if (record.getBasicAlwaysFullScreen() != null) {
            sql.SET("basic_always_full_screen = #{basicAlwaysFullScreen,jdbcType=BIT}");
        }
        
        if (record.getCustomUrl() != null) {
            sql.SET("custom_url = #{customUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomUsername() != null) {
            sql.SET("custom_username = #{customUsername,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomPassword() != null) {
            sql.SET("custom_password = #{customPassword,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomSendToken() != null) {
            sql.SET("custom_send_token = #{customSendToken,jdbcType=BIT}");
        }
        
        if (record.getAdditionalInfo() != null) {
            sql.SET("additional_info = #{additionalInfo,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_registration
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, OAuth2RegistrationExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}
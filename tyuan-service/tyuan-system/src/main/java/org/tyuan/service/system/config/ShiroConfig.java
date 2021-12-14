/**
 * @ClassName ShiroConfig
 * @Author dev@tyuan.design
 * @Date 2020/6/24 9:58
 * <p>
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
 * <p>
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
 * <p>
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
package org.tyuan.service.system.config;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tyuan.service.system.cache.UserInfoCacheService;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private static final String ROUTER_SYS_LOGIN_ACCOUNT = "/api/login/account";

    @Resource
    UserInfoCacheService userInfoCacheService;

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * 安全管理配置
     *
     * @param customRealm
     * @return
     */
    @Bean
    public SecurityManager securityManager(@Qualifier("customRealm") CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 配置
        securityManager.setRealm(customRealm);

        // session 配置
        securityManager.setSessionManager(sessionManager());

        // 禁用Session作为存储策略的实现。
        DefaultSubjectDAO defaultSubjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluatord = (DefaultSessionStorageEvaluator) defaultSubjectDAO
                .getSessionStorageEvaluator();
        defaultSessionStorageEvaluatord.setSessionStorageEnabled(false);

        return securityManager;
    }

    /**
     * sessionManager通过sessionValidationSchedulerEnabled禁用掉会话调度器，
     * 因为我们禁用掉了会话，所以没必要再定期过期会话了。
     *
     * @return
     */
    @Bean
    public DefaultSessionManager sessionManager() {
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 自定义过滤器
        shiroFilterFactoryBean.getFilters().put("statelessAuthc", new StatelessAccessControlFilter(userInfoCacheService));
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        //对所有用户认证
        map.put("/**", "statelessAuthc");
        map.put("/demo/**", "anon");
        map.put(ROUTER_SYS_LOGIN_ACCOUNT, "anon");
        // 自定义过滤器
        //map.put("customer", {});
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean(SecurityManager securityManager) throws Exception {
        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter((Filter) shiroFilterFactoryBean(securityManager).getObject());
        filterRegistration.setAsyncSupported(true);
        filterRegistration.setEnabled(true);

        //支持异步：DispatcherType.ASYNC
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);

        return filterRegistration;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

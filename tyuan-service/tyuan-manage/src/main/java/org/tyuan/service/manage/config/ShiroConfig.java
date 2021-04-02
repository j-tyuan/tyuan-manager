/**
 * @ClassName ShiroConfig
 * @Author dev@tyuan.design
 * @Date 2020/6/24 9:58
 */
package org.tyuan.service.manage.config;

import org.tyuan.service.manage.cache.UserInfoCacheService;
import org.tyuan.service.manage.web.RouteConstant;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

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
        map.put(RouteConstant.ROUTER_SYS_LOGIN_ACCOUNT, "anon");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

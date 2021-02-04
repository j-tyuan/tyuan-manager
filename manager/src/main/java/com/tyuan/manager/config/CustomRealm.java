/**
 * @ClassName CustomRealm
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:48
 */
package com.tyuan.manager.config;

import com.tyuan.manager.cache.UserInfoCacheService;
import com.tyuan.manager.service.SysPermissionService;
import com.tyuan.manager.service.SysRoleService;
import com.tyuan.manager.service.SysUserService;
import com.tyuan.manager.utils.UserInfoHolder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    // 必须要延迟加载
    @Lazy
    UserInfoCacheService userInfoCacheService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService roleService;

    @Autowired
    SysPermissionService sysPermissionService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String token = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();

        // 在缓存中拿到用户的角色信息，角色信息在用户登陆的时候加载到缓存
        List<String> roles = (List<String>) userInfoCacheService.getRole(token);
        if (CollectionUtils.isNotEmpty(roles)) {

            simpleAuthenticationInfo.addRoles(roles);
        }

        // 在缓存中拿到用户的权限信息，权限信息在用户登陆的时候加载到缓存
        List<String> perm = (List<String>) userInfoCacheService.getPerm(token);
        if (CollectionUtils.isNotEmpty(perm)) {

            simpleAuthenticationInfo.addStringPermissions(perm);
        }
        //添加 认证用户 权限，公共权限
        simpleAuthenticationInfo.addStringPermission("authc");


        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }

        // 获取用户的token，有时间对token与IP绑定及增加校验
        String token = authenticationToken.getPrincipal().toString();

        // 根据token在缓存中拿到用户信息
        Map user = (Map) userInfoCacheService.get(token);
        if (user == null) {
            return null;
        } else {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    token,
                    authenticationToken.getCredentials(),
                    getName());

            UserInfoHolder.put(user);
            // 加载用户到内存
            return simpleAuthenticationInfo;
        }
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessAuthenticationToken;
    }

    /**
     * 用户权限校验
     *
     * @param permission
     * @param info
     * @return
     */
    @Override
    protected boolean isPermitted(Permission permission, AuthorizationInfo info) {
        Collection<String> list = info.getRoles();
        if (CollectionUtils.isNotEmpty(list)) {
            for (String item : list) {
                if ("admin".equals(item)) {
                    return true;
                }
            }
        }

        return super.isPermitted(permission, info);
    }

    @Override
    protected boolean hasRole(String roleIdentifier, AuthorizationInfo info) {

        Collection<String> list = info.getRoles();
        if (CollectionUtils.isNotEmpty(list)) {
            for (String item : list) {
                if ("admin".equals(item)) {
                    return true;
                }
            }
        }

        return super.hasRole(roleIdentifier, info);
    }
}

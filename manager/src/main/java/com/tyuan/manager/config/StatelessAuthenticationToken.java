/**
 * @ClassName StatelessAuthenticationToken
 * @Author dev@tyuan.design
 * @Date 2020/6/24 13:33
 */
package com.tyuan.manager.config;

import org.apache.shiro.authc.AuthenticationToken;

public class StatelessAuthenticationToken implements AuthenticationToken {

    private String token;

    public StatelessAuthenticationToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this;
    }

}

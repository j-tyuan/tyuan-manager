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
package org.tyuan.service.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.tyuan.service.application.exception.TyuanErrorResponseHandler;
import org.tyuan.service.application.service.security.auth.jwt.*;
import org.tyuan.service.application.service.security.auth.jwt.extractor.TokenExtractor;
import org.tyuan.service.application.service.security.auth.rest.RestAuthenticationProvider;
import org.tyuan.service.application.service.security.auth.rest.RestLoginProcessingFilter;
import org.tyuan.service.application.service.security.auth.rest.RestPublicLoginProcessingFilter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jiangguiqi@aliyun.com
 * @version 1.0
 * @date 2021/12/21 10:32 上午
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private TyuanErrorResponseHandler restAccessDeniedHandler;

    @Resource
    private AuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

    @Resource
    private AuthenticationFailureHandler defaultAuthenticationFailureHandler;

    @Resource
    private TokenExtractor jwtHeaderTokenExtractor;

    @Resource
    private TokenExtractor jwtQueryTokenExtractor;

    @Resource
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private RestAuthenticationProvider restAuthenticationProvider;
    @Resource
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Resource
    private RefreshTokenAuthenticationProvider refreshTokenAuthenticationProvider;


    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String JWT_TOKEN_HEADER_PARAM_V2 = "Authorization";
    public static final String JWT_TOKEN_QUERY_PARAM = "token";

    public static final String WEBJARS_ENTRY_POINT = "/webjars/**";
    public static final String DEVICE_API_ENTRY_POINT = "/api/v1/**";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/api/auth/login";
    public static final String PUBLIC_LOGIN_ENTRY_POINT = "/api/auth/login/public";
    public static final String TOKEN_REFRESH_ENTRY_POINT = "/api/auth/token";
    protected static final String[] NON_TOKEN_BASED_AUTH_ENTRY_POINTS = new String[]{"/index.html", "/assets/**", "/static/**", "/api/noauth/**", "/webjars/**", "/api/license/**"};
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/**";
    public static final String WS_TOKEN_BASED_AUTH_ENTRY_POINT = "/api/ws/**";


    protected RestLoginProcessingFilter buildRestLoginProcessingFilter() throws Exception {
        RestLoginProcessingFilter filter = new RestLoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT, defaultAuthenticationSuccessHandler, defaultAuthenticationFailureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    protected RestPublicLoginProcessingFilter buildRestPublicLoginProcessingFilter() throws Exception {
        RestPublicLoginProcessingFilter filter = new RestPublicLoginProcessingFilter(PUBLIC_LOGIN_ENTRY_POINT, defaultAuthenticationSuccessHandler, defaultAuthenticationFailureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = new ArrayList<>(Arrays.asList(NON_TOKEN_BASED_AUTH_ENTRY_POINTS));
        pathsToSkip.addAll(Arrays.asList(WS_TOKEN_BASED_AUTH_ENTRY_POINT, TOKEN_REFRESH_ENTRY_POINT, FORM_BASED_LOGIN_ENTRY_POINT,
                PUBLIC_LOGIN_ENTRY_POINT, DEVICE_API_ENTRY_POINT, WEBJARS_ENTRY_POINT));
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        JwtTokenAuthenticationProcessingFilter filter
                = new JwtTokenAuthenticationProcessingFilter(defaultAuthenticationFailureHandler, jwtHeaderTokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    protected RefreshTokenProcessingFilter buildRefreshTokenProcessingFilter() throws Exception {
        RefreshTokenProcessingFilter filter = new RefreshTokenProcessingFilter(TOKEN_REFRESH_ENTRY_POINT, defaultAuthenticationSuccessHandler, defaultAuthenticationFailureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    protected JwtTokenAuthenticationProcessingFilter buildWsJwtTokenAuthenticationProcessingFilter() throws Exception {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(WS_TOKEN_BASED_AUTH_ENTRY_POINT);
        JwtTokenAuthenticationProcessingFilter filter
                = new JwtTokenAuthenticationProcessingFilter(defaultAuthenticationFailureHandler, jwtQueryTokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(restAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
        auth.authenticationProvider(refreshTokenAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().cacheControl().and().frameOptions().disable()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/error").permitAll()
                .antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(restAccessDeniedHandler)
                .and()
                .addFilterBefore(buildRestLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildRestPublicLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildRefreshTokenProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildWsJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
        ;

    }
}

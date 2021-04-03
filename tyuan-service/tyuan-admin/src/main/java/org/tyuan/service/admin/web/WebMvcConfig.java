/**
 * @ClassName WebMvcConfig
 * @Author dev@tyuan.design
 * @Date 2020/5/28 15:55
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
package org.tyuan.service.admin.web;

import org.tyuan.common.utils.UserInfoHolder;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebMvcConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest rst = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        //跨域设置,谁来都放行,与设置成*效果相同,但是这里设置成*行不通,因此用该方法代替
        rep.setHeader("Access-Control-Allow-Origin", rst.getHeader("Origin"));
        rep.setHeader("Access-Control-Allow-Credentials", "true");
        //不能设置成*,否则跨域请求会失败
        rep.setHeader("Access-Control-Allow-Methods", "POST,PUT, GET, OPTIONS, DELETE");
        rep.setHeader("Access-Control-Max-Age", "3600");
        //我这里需要放行这三个header头部字段
        rep.setHeader("Access-Control-Allow-Headers", "content-type,x-requested-with,token");
        chain.doFilter(rst, rep);

        UserInfoHolder.clear();
    }

    @Override
    public void destroy() {

    }
}

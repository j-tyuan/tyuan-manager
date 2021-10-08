/**
 * @ClassName StatelessAccessControlFilter
 * @Author dev@tyuan.design
 * @Date 2020/6/24 13:28
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
package org.tyuan.service.framework.config;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tyuan.service.system.model.ResultData;
import org.tyuan.service.framework.cache.UserInfoCacheService;
import org.tyuan.service.system.model.ErrorCodeConsts;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class StatelessAccessControlFilter extends AccessControlFilter {

    Logger logger = LoggerFactory.getLogger(StatelessAccessControlFilter.class);

    UserInfoCacheService userInfoCacheService;

    private static final String TOKEN = "utoken";

    public StatelessAccessControlFilter(UserInfoCacheService userInfoCacheService) {
        this.userInfoCacheService = userInfoCacheService;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
        String token = null;
        if (null != cookies) {
            for (Cookie item : cookies) {
                String name = item.getName();
                if (TOKEN.equals(name)) {
                    try {
                        token = item.getValue().split(":")[1];
                        StatelessAuthenticationToken tn = new StatelessAuthenticationToken(token);
                        getSubject(servletRequest, servletResponse).login(tn);
                        return true;
                    } catch (Exception e) {
                        logger.error("【无状态登陆过滤】登陆失败 {}", e.getMessage());
                        break;
                    }
                }
            }
        }

        String message = userInfoCacheService.readeLeaveMessage(token);
        sendError(message, ErrorCodeConsts.ERROR_LOGIN, (HttpServletResponse) servletResponse);
        return false;
    }

    public void sendError(String message, int status, HttpServletResponse response) throws IOException {
        ResultData resultData = new ResultData();
        resultData.setErrorMessage(message);
        resultData.setErrorCode(status);
        resultData.setSuccess(true);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(resultData.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }

}

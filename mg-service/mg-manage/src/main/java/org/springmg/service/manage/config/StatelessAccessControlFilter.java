/**
 * @ClassName StatelessAccessControlFilter
 * @Author dev@tyuan.design
 * @Date 2020/6/24 13:28
 */
package org.springmg.service.manage.config;

import org.springmg.service.manage.cache.UserInfoCacheService;
import org.springmg.service.manage.web.WebConstant;
import org.springmg.service.model.ErrorCodeConsts;
import org.springmg.service.model.ResultData;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                if (WebConstant.TOKEN.equals(name)) {
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

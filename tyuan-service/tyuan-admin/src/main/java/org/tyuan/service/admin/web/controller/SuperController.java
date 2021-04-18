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
package org.tyuan.service.admin.web.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tyuan.common.ResultData;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.common.utils.UserInfoHolder;
import org.tyuan.service.admin.web.WebConstant;
import org.tyuan.service.system.model.ErrorCodeConsts;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class SuperController {

    Logger logger = LoggerFactory.getLogger(SuperController.class);

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ResultData serviceException(ServiceException e) {
        ResultData resultData = new ResultData();
        return resultData
                .setErrorMessage(e.getMessage())
                .setErrorCode(e.getStatus());
    }

    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultData methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldError().getDefaultMessage();
        logger.warn("参数验证失败 {}  -  {} message: {}", UserInfoHolder.getUserId(), UserInfoHolder.getUserName(), msg);
        ResultData resultData = new ResultData();
        return resultData
                .setErrorMessage(msg)
                .setErrorCode(ErrorCodeConsts.ERROR_PARAM);
    }


    @ResponseBody
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public ResultData unauthorizedException(Exception e) {
        ResultData resultData = new ResultData();
        logger.warn("权限不足 {}  -  {}", UserInfoHolder.getUserId(), UserInfoHolder.getUserName());
        return resultData
                .setErrorMessage("权限不足")
                .setErrorCode(ErrorCodeConsts.ERROR_NO_PERMISSION);
    }

    @ResponseBody
    @ExceptionHandler(BadSqlGrammarException.class)
    public ResultData badSqlGrammarException(Exception e) {
        ResultData resultData = new ResultData();
        e.printStackTrace();
        logger.warn("演示版本不允许操作 {}  -  {}", UserInfoHolder.getUserId(), UserInfoHolder.getUserName());
        return resultData
                .setErrorMessage("当前为演示版本，您的操作被禁止")
                .setErrorCode(ErrorCodeConsts.ERROR);
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultData exception(Exception e) {
        ResultData resultData = new ResultData();
        e.printStackTrace();
        logger.warn("未知错误 {}  -  {}", UserInfoHolder.getUserId(), UserInfoHolder.getUserName());
        return resultData
                .setErrorMessage("系统正在修复中...")
                .setErrorCode(ErrorCodeConsts.ERROR);
    }

    @ModelAttribute("userToken")
    public String getUserId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            return null;
        }
        for (Cookie item : cookies) {
            String name = item.getName();
            if (WebConstant.TOKEN.equals(name)) {
                String token = item.getValue().split(":")[1];
                return token;
            }
        }
        return null;
    }
}

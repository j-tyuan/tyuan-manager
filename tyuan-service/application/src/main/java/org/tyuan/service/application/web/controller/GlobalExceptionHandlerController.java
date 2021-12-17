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
package org.tyuan.service.application.web.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.common.utils.UserInfoHolder;
import org.tyuan.service.data.ErrorCodeConsts;
import org.tyuan.service.data.ResultData;

/**
 * 全局异常处理
 *
 * @author guiqijiang
 */
@ControllerAdvice
public class GlobalExceptionHandlerController {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    /**
     * 全局：业务异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ResultData serviceExceptionHandler(ServiceException e) {
        ResultData resultData = new ResultData();
        return resultData
                .setErrorMessage(e.getMessage())
                .setErrorCode(e.getStatus());
    }

    /**
     * 全局：参数异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultData methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldError().getDefaultMessage();
        logger.warn("参数验证失败 {}  -  {} message: {}", UserInfoHolder.getUserId(), UserInfoHolder.getUserName(), msg);
        ResultData resultData = new ResultData();
        return resultData
                .setErrorMessage(msg)
                .setErrorCode(ErrorCodeConsts.ERROR_PARAM);
    }

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultData httpRequestMethodNotSupportedException(Exception e) {

        ResultData resultData = new ResultData();
        e.printStackTrace();
        return resultData
                .setErrorMessage("请求方法不正确")
                .setErrorCode(ErrorCodeConsts.ERROR);
    }

    /**
     * 入参格式错误，调用者传入了一个非法的参数：Integer 传成 String
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultData httpMessageNotReadableException(Exception e) {
        ResultData resultData = new ResultData();
        e.printStackTrace();
        return resultData
                .setErrorMessage("入参格式错误")
                .setErrorCode(ErrorCodeConsts.ERROR);
    }


    /**
     * 全局：权限异常处理
     *
     * @param e
     * @return
     */
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

    /**
     * 全局：异常处理
     *
     * @param e
     * @return
     */
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
}

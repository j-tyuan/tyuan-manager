package com.tyuan.manager.base.web.controller;

import com.tyuan.common.exception.ServiceException;
import com.tyuan.manager.base.utils.UserInfoHolder;
import com.tyuan.manager.base.web.WebConstant;
import com.tyuan.model.base.ErrorCodeConsts;
import com.tyuan.model.base.ResultData;
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

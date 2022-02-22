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

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.application.exception.TyuanCredentialsExpiredResponse;
import org.tyuan.service.application.exception.TyuanErrorResponse;
import org.tyuan.service.application.service.security.exception.AuthMethodNotSupportedException;
import org.tyuan.service.application.service.security.exception.JwtExpiredTokenException;
import org.tyuan.service.application.service.security.exception.UserPasswordExpiredException;
import org.tyuan.service.data.ResultData;
import org.tyuan.service.data.exception.TyuanErrorCode;
import org.tyuan.service.data.exception.TyuanException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author guiqijiang
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler implements AccessDeniedHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    private static final Map<HttpStatus, TyuanErrorCode> statusToErrorCodeMap = new HashMap<>();

    static {
        statusToErrorCodeMap.put(HttpStatus.BAD_REQUEST, TyuanErrorCode.BAD_REQUEST_PARAMS);
        statusToErrorCodeMap.put(HttpStatus.UNAUTHORIZED, TyuanErrorCode.AUTHENTICATION);
        statusToErrorCodeMap.put(HttpStatus.FORBIDDEN, TyuanErrorCode.PERMISSION_DENIED);
        statusToErrorCodeMap.put(HttpStatus.NOT_FOUND, TyuanErrorCode.ITEM_NOT_FOUND);
        statusToErrorCodeMap.put(HttpStatus.METHOD_NOT_ALLOWED, TyuanErrorCode.BAD_REQUEST_PARAMS);
        statusToErrorCodeMap.put(HttpStatus.NOT_ACCEPTABLE, TyuanErrorCode.BAD_REQUEST_PARAMS);
        statusToErrorCodeMap.put(HttpStatus.UNSUPPORTED_MEDIA_TYPE, TyuanErrorCode.BAD_REQUEST_PARAMS);
        statusToErrorCodeMap.put(HttpStatus.TOO_MANY_REQUESTS, TyuanErrorCode.TOO_MANY_REQUESTS);
        statusToErrorCodeMap.put(HttpStatus.INTERNAL_SERVER_ERROR, TyuanErrorCode.GENERAL);
        statusToErrorCodeMap.put(HttpStatus.SERVICE_UNAVAILABLE, TyuanErrorCode.GENERAL);
    }

    private static final Map<TyuanErrorCode, HttpStatus> errorCodeToStatusMap = new HashMap<>();

    static {
        errorCodeToStatusMap.put(TyuanErrorCode.GENERAL, HttpStatus.INTERNAL_SERVER_ERROR);
        errorCodeToStatusMap.put(TyuanErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED);
        errorCodeToStatusMap.put(TyuanErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
        errorCodeToStatusMap.put(TyuanErrorCode.CREDENTIALS_EXPIRED, HttpStatus.UNAUTHORIZED);
        errorCodeToStatusMap.put(TyuanErrorCode.PERMISSION_DENIED, HttpStatus.FORBIDDEN);
        errorCodeToStatusMap.put(TyuanErrorCode.INVALID_ARGUMENTS, HttpStatus.BAD_REQUEST);
        errorCodeToStatusMap.put(TyuanErrorCode.BAD_REQUEST_PARAMS, HttpStatus.BAD_REQUEST);
        errorCodeToStatusMap.put(TyuanErrorCode.ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
        errorCodeToStatusMap.put(TyuanErrorCode.TOO_MANY_REQUESTS, HttpStatus.TOO_MANY_REQUESTS);
        errorCodeToStatusMap.put(TyuanErrorCode.TOO_MANY_UPDATES, HttpStatus.TOO_MANY_REQUESTS);
        errorCodeToStatusMap.put(TyuanErrorCode.SUBSCRIPTION_VIOLATION, HttpStatus.FORBIDDEN);
    }

    private static TyuanErrorCode statusToErrorCode(HttpStatus status) {
        return statusToErrorCodeMap.getOrDefault(status, TyuanErrorCode.GENERAL);
    }

    private static HttpStatus errorCodeToStatus(TyuanErrorCode errorCode) {
        return errorCodeToStatusMap.getOrDefault(errorCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Autowired
    private ObjectMapper mapper;

    @Override
    @ExceptionHandler(AccessDeniedException.class)
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            mapper.writeValue(response.getWriter(),
                    TyuanErrorResponse.of("You don't have permission to perform this operation!",
                            TyuanErrorCode.PERMISSION_DENIED, HttpStatus.FORBIDDEN));
        }
    }

    @ExceptionHandler(Exception.class)
    public void handle(Exception exception, HttpServletResponse response) {
        exception.printStackTrace();
        logger.error(exception.getMessage());
        if (!response.isCommitted()) {
            try {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                if (exception instanceof TyuanException) {
                    TyuanException thingsboardException = (TyuanException) exception;
                    if (thingsboardException.getErrorCode() == TyuanErrorCode.SUBSCRIPTION_VIOLATION) {
                        handleSubscriptionException((TyuanException) exception, response);
                    } else {
                        handleThingsboardException((TyuanException) exception, response);
                    }
                } else if (exception instanceof AccessDeniedException) {
                    handleAccessDeniedException(response);
                } else if (exception instanceof AuthenticationException) {
                    handleAuthenticationException((AuthenticationException) exception, response);
                } else {
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    mapper.writeValue(response.getWriter(), TyuanErrorResponse.of(exception.getMessage(),
                            TyuanErrorCode.GENERAL, HttpStatus.INTERNAL_SERVER_ERROR));
                }
            } catch (IOException e) {
                logger.error("Can't handle exception", e);
            }
        }
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NotNull Exception ex, @Nullable Object body,
            @NotNull HttpHeaders headers, @NotNull HttpStatus status,
            @NotNull WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        TyuanErrorCode errorCode = statusToErrorCode(status);
        logger.error(ex.getMessage());
        return new ResponseEntity<>(TyuanErrorResponse.of(ex.getMessage(), errorCode, status), headers, status);
    }

    private void handleThingsboardException(TyuanException thingsboardException, HttpServletResponse response) throws IOException {
        TyuanErrorCode errorCode = thingsboardException.getErrorCode();
        HttpStatus status = errorCodeToStatus(errorCode);
        response.setStatus(status.value());
        mapper.writeValue(response.getWriter(), TyuanErrorResponse.of(thingsboardException.getMessage(), errorCode, status));
    }

    private void handleSubscriptionException(TyuanException subscriptionException, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        mapper.writeValue(response.getWriter(),
                (new ObjectMapper()).readValue(((HttpClientErrorException) subscriptionException.getCause()).getResponseBodyAsByteArray(), Object.class));
    }

    private void handleAccessDeniedException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        mapper.writeValue(response.getWriter(),
                TyuanErrorResponse.of("You don't have permission to perform this operation!",
                        TyuanErrorCode.PERMISSION_DENIED, HttpStatus.FORBIDDEN));

    }

    private void handleAuthenticationException(AuthenticationException authenticationException, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        if (authenticationException instanceof BadCredentialsException || authenticationException instanceof UsernameNotFoundException) {
            mapper.writeValue(response.getWriter(), TyuanErrorResponse.of("Invalid username or password", TyuanErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        } else if (authenticationException instanceof DisabledException) {
            mapper.writeValue(response.getWriter(), TyuanErrorResponse.of("User account is not active", TyuanErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        } else if (authenticationException instanceof LockedException) {
            mapper.writeValue(response.getWriter(), TyuanErrorResponse.of("User account is locked due to security policy", TyuanErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        } else if (authenticationException instanceof JwtExpiredTokenException) {
            mapper.writeValue(response.getWriter(), TyuanErrorResponse.of("Token has expired", TyuanErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
        } else if (authenticationException instanceof AuthMethodNotSupportedException) {
            mapper.writeValue(response.getWriter(), TyuanErrorResponse.of(authenticationException.getMessage(), TyuanErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        } else if (authenticationException instanceof UserPasswordExpiredException) {
            UserPasswordExpiredException expiredException = (UserPasswordExpiredException) authenticationException;
            String resetToken = expiredException.getResetToken();
            mapper.writeValue(response.getWriter(), TyuanCredentialsExpiredResponse.of(expiredException.getMessage(), resetToken));
        } else {
            mapper.writeValue(response.getWriter(), TyuanErrorResponse.of("Authentication failed", TyuanErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        }
    }


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
}

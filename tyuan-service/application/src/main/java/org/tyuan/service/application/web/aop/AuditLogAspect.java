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
package org.tyuan.service.application.web.aop;

import com.google.common.collect.Lists;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tyuan.common.utils.IPUtils;
import org.tyuan.common.utils.MDCUtils;
import org.tyuan.service.common.RequestContext;
import org.tyuan.service.common.annotation.AuditLog;
import org.tyuan.service.application.service.AuditLogService;
import org.tyuan.service.application.service.security.model.SecurityUser;
import org.tyuan.service.data.LogTransport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author guiqijiang
 */
@Aspect
@Component
public class AuditLogAspect {

    @Resource
    private AuditLogService auditLogService;

    @Pointcut("@annotation( org.tyuan.service.common.annotation.AuditLog)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        RequestContext.initContext();

        String requestId = RequestContext.getRequestId();
        RequestContext.Context context = RequestContext.get();
        //获取用户ip地址
        ServletRequestAttributes httpServletRequest = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String addr = IPUtils.getIpAddr(httpServletRequest.getRequest());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        String userName = "anonymous";
        Long userId = -1L;
        if (null != securityContext) {
            SecurityUser principal = (SecurityUser) securityContext.getAuthentication().getPrincipal();
            userName = principal.getUserName();
            userId = principal.getId();
        }

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取请求的方法名
        String methodName = method.getName();
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //请求的参数
        Object[] args = joinPoint.getArgs();

        String mdcKey = requestId + "-" + userId + "-" + userName + "-" + addr;
        MDCUtils.setTraceId(mdcKey);

        final Long finalUserId = userId;
        final String finalUserName = userName;
        context.setLogConsumer(logObj -> {
            LogTransport transport = (LogTransport) logObj;
            CompletableFuture.runAsync(() -> {
                try {
                    //获取操作
                    AuditLog var = method.getAnnotation(AuditLog.class);
                    String title = "";
                    if (var != null) {
                        title = var.value();
                    }

                    List params = Lists.newArrayList();
                    for (Object o : args) {
                        if (o instanceof Serializable) {
                            params.add(o);
                        }
                    }

                    try {
                        String classMethod = className + "." + methodName;
                        auditLogService.logAction(finalUserId, finalUserName, var.type(), transport.getException(), classMethod, title, params, transport.getDuration(), requestId, addr);
                    } catch (Exception e) {
                        e.fillInStackTrace();
                    }
                } finally {
                    MDCUtils.removeTraceId();
                    MDCUtils.clear();
                }

            });
        });

    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void handleThrowing(JoinPoint joinPoint, Exception e) {
        Long time = System.currentTimeMillis() - RequestContext.getStartTime();
        RequestContext.Context context = RequestContext.get();
        RequestContext.remove();

        context.getLogConsumer().accept(new LogTransport(time, e));
    }

    @AfterReturning(pointcut = "logPointCut()")
    public void afterReturning() {
        Long time = System.currentTimeMillis() - RequestContext.getStartTime();
        RequestContext.Context context = RequestContext.get();
        RequestContext.remove();
        context.getLogConsumer().accept(new LogTransport(time, null));
    }


}

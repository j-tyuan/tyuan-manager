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
package org.tyuan.service.admin.web.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tyuan.common.annotation.Log;
import org.tyuan.common.utils.IPUtils;
import org.tyuan.common.utils.RequestContext;
import org.tyuan.common.utils.UserInfoHolder;
import org.tyuan.service.system.model.pojo.SysLogWithBLOBs;
import org.tyuan.service.system.service.SysLogService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
public class LogAspect {

    @Resource
    private SysLogService sysLogService;

    @Pointcut("@annotation( org.tyuan.common.annotation.Log)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        RequestContext.initContext();

        String requestId = RequestContext.getRequestId();
        RequestContext.Context context = RequestContext.get();
        //获取用户ip地址
        RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes httpServletRequest = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String addr = IPUtils.getIpAddr(httpServletRequest.getRequest());
        String userName = UserInfoHolder.getUserName();
        Long userId = UserInfoHolder.getUserId();
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

        // 异步处理日志
        CompletableFuture.runAsync(() -> {
            //保存日志
            SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
            //获取操作
            Log var = method.getAnnotation(Log.class);
            if (var != null) {
                String value = var.value();
                sysLog.setLogTitle(value);//保存获取的操作
            }
            sysLog.setMethod(className + "." + methodName);
            List params = Lists.newArrayList();
            for (Object o : args) {
                if (o instanceof Serializable) {
                    params.add(o);
                }
            }
            sysLog.setLogType(var.type().getType());
            //将参数所在的数组转换成json
            sysLog.setUserAgent(JSONObject.toJSONString(params));
            sysLog.setRequestId(requestId);
            sysLog.setCreateTime(new Date());
            sysLog.setUserName(userName);
            sysLog.setUserId(userId);
            sysLog.setRemoteAddr(addr);
            sysLog.setDuration(-1);
            try {
                //调用service保存SysLog实体类到数据库
                synchronized (context) {
                    sysLogService.add(sysLog);
                }
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        });
    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void handleThrowing(JoinPoint joinPoint, Exception e) {
        Long time = System.currentTimeMillis() - RequestContext.getStartTime();
        String requestId = RequestContext.getRequestId();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestContext.Context context = RequestContext.get();
        RequestContext.remove();
        CompletableFuture.runAsync(() -> {
            //开始打log
            Map map = Maps.newHashMap();
            map.put("异常信息", e.toString());
            map.put("类", className);
            map.put("方法", methodName);

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                builder.append(args[i].toString());
            }
            map.put("参数", builder.toString());

            SysLogWithBLOBs withBLOBs = new SysLogWithBLOBs();
            withBLOBs.setException(JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue));
            withBLOBs.setDuration(time.intValue());
            try {
                synchronized (context) {
                    sysLogService.updateByRequestId(requestId, withBLOBs);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    @AfterReturning(pointcut = "logPointCut()")
    public void afterReturning() {
        Long time = System.currentTimeMillis() - RequestContext.getStartTime();
        String requestId = RequestContext.getRequestId();
        RequestContext.Context context = RequestContext.get();
        RequestContext.remove();
        CompletableFuture.runAsync(() -> {
            SysLogWithBLOBs withBLOBs = new SysLogWithBLOBs();
            withBLOBs.setDuration(time.intValue());
            // 需要等待，日志插入完成后才能执行更改操作
            synchronized (context) {
                try {
                    sysLogService.updateByRequestId(requestId, withBLOBs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

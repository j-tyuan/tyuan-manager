/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/2/4 6:32 下午
 */
package com.tyuan.manager.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tyuan.common.utils.IPUtils;
import com.tyuan.common.utils.Tools;
import com.tyuan.manager.annotation.Log;
import com.tyuan.manager.service.SysLogService;
import com.tyuan.manager.utils.UserInfoHolder;
import com.tyuan.model.pojo.SysLogWithBLOBs;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

    @Resource
    private SysLogService sysLogService;

    private static final ThreadLocal<String> REQUEST_ID = new ThreadLocal<>();

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.tyuan.manager.annotation.Log)")
    public void logPointCut() {
    }


    //切面 配置通知
    @Before("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {

        String uuid = Tools.generateUUID();
        REQUEST_ID.set(uuid);

        //保存日志
        SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        Log var = method.getAnnotation(Log.class);
        if (var != null) {
            String value = var.value();
            sysLog.setTitle(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        List params = Lists.newArrayList();
        for (Object o : args) {
            if (o instanceof Serializable){
                params.add(o);
            }
        }
        //将参数所在的数组转换成json
        sysLog.setUserAgent(JSONObject.toJSONString(params));

        sysLog.setRequestId(uuid);
        sysLog.setCreateDate(new Date());

        sysLog.setUserName(UserInfoHolder.getUserName());
        sysLog.setUserId(UserInfoHolder.getUserId());

        //获取用户ip地址
        RequestContextHolder.getRequestAttributes();

        ServletRequestAttributes httpServletRequest = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String addr = IPUtils.getIpAddr(httpServletRequest.getRequest());
        sysLog.setRemoteAddr(addr);

        try {
            //调用service保存SysLog实体类到数据库
            sysLogService.add(sysLog);
        } catch (Exception e) {
            e.fillInStackTrace();
        }

    }


    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void handleThrowing(JoinPoint joinPoint, Exception e) {

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();
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
        sysLogService.saveExceptionInfo(REQUEST_ID.get(), JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue));
        REQUEST_ID.remove();
    }

    @AfterReturning(pointcut = "logPointCut()")
    public void afterReturning() {
        REQUEST_ID.remove();
    }
}

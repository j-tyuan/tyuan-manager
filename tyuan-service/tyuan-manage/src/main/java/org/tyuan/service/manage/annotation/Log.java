package org.tyuan.service.manage.annotation;


import org.tyuan.service.manage.aop.LogAspect;

import java.lang.annotation.*;

/**
 * @author jiangguiqi@aliyun.com
 * @version 1.0
 * @date 2021/2/4 6:25 下午
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {

    String value() default "";

    LogAspect.LogType type();

}

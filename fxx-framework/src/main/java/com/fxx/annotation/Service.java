package com.fxx.annotation;

import java.lang.annotation.*;

/**
 * @Description: Service的注解
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/17 10:59
 * @Version: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    /**
     * 表示必须注入 没有则报错
     * @return
     */
    String value() default "";
}

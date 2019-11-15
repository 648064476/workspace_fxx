package com.fxx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: post请求注解
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/17 10:32
 * @Version: 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PostMapping {

    /**
     * 此映射的名称
     * @return
     */
    String name() default "";

    /**
     * 此映射的路径
     * @return
     */
    String[] value() default {};
}

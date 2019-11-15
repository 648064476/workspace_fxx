package com.fxx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求路径注解
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

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

    /**
     * 此映射的方法类型
     * @return
     */
    RequestMethod[] method() default {};

}

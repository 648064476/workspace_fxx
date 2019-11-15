package com.fxx.annotation;

import java.lang.annotation.*;

/**
 * @Description: 属性注入注解
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/17 11:04
 * @Version: 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    /**
     * 表示必须注入 没有则报错
     * @return
     */
    boolean required() default true;
}


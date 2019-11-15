package com.fxx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 表示未控制器 加载在类上面
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/17 10:10
 * @Version: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

    /**
     * 该值表示组件名称
     * @return
     */
    String value() default "";
}

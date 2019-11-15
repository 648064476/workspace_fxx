package com.fxx.annotation;

import java.lang.annotation.*;

/**
 * @Description: 表单中数据
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/20 16:53
 * @Version: 1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {

    String value() default "";
}

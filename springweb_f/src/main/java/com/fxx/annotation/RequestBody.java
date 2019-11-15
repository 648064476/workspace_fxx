package com.fxx.annotation;

import java.lang.annotation.*;

/**
 * @Description: 从流中取数据
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/20 16:50
 * @Version: 1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestBody {

    boolean required() default true;

}

package com.fxx.proxy;

import java.lang.annotation.*;

/**
 * @Description: 切面注解
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/29 14:57
 * @Version: 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}

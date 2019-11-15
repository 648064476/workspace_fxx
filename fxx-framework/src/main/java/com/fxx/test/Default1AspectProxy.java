package com.fxx.test;

import com.fxx.annotation.RestController;
import com.fxx.proxy.Aspect;
import com.fxx.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * @Description: 自定义切面实现方法
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/29 15:38
 * @Version: 1.0
 */
@Aspect(RestController.class)
public class Default1AspectProxy extends AspectProxy {

    @Override
    protected void end() {
        System.out.println(" 2  ---------- end()方法");
    }

    @Override
    protected void error(Class<?> targetClass, Method targetMethod, Object[] methodParam) {
        System.out.println(" 2  ---------- error()方法");
    }

    @Override
    protected void after(Class<?> targetClass, Method targetMethod, Object[] methodParam, Object result) {
        System.out.println(" 2  ---------- after()方法");
    }

    @Override
    protected void begin() {
        System.out.println(" ---------- begin()方法");
    }

    @Override
    protected void before() {
        System.out.println(" 2  ---------- before()方法");
        
    }
}

package com.fxx.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * @Description: 反射工具类
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/17 13:56
 * @Version: 1.0
 */
public class ReflectionUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtils.class);


    /**
     * 创建实例
     *
     * @param cla
     * @return
     */
    public static Object newInstance(Class<?> cla) {
        Object bean = null;
        try {
            bean = cla.newInstance();
        } catch (Exception e) {
            LOGGER.error("创建对象失败", e);
            throw new RuntimeException(e);
        }
        return bean;
    }

    /**
     * 调用方法
     *
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result = null;
        try {
            result = method.invoke(obj, args);
        } catch (Exception e) {
            LOGGER.error("方法调用失败", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     * @param obj
     * @param field
     * @param value
     */
    public static void setFiled(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            LOGGER.error("设置参数失败", e);
            throw new RuntimeException(e);
        }

    }
}

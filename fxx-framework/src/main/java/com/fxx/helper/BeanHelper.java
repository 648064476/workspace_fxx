package com.fxx.helper;

import com.fxx.util.ReflectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description: bean助手类
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/17 14:06
 * @Version: 1.0
 */
public class BeanHelper {

    /**
     *
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        beanClassSet.forEach(obj ->{
            BEAN_MAP.put(obj, ReflectionUtils.newInstance(obj));
        });
    }

    /**
     * 获取bean映射
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取bean实例
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> tClass){
        if (!BEAN_MAP.containsKey(tClass)){
            throw new RuntimeException("不能够获取到bean"+tClass);
        }
        return (T) BEAN_MAP.get(tClass);
    }

    /**
     * 设置bean实例
     * @param clazz
     * @param obj
     */
    public static void setBean(Class < ? > clazz,Object obj){
        BEAN_MAP.put(clazz, obj);
    }
}

package com.fxx.helper;

import com.fxx.annotation.Controller;
import com.fxx.annotation.RestController;
import com.fxx.annotation.Service;
import com.fxx.config.ConfigHelper;
import com.fxx.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: class助手类
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/17 11:29
 * @Version: 1.0
 */
public class ClassHelper {

    /**
     * class类集合
     */
    private static Set<Class<?>> CLASS_SET = null;

    static {
        try {
            String basePackage = (String) ConfigHelper.getBasePackage();
            CLASS_SET = ClassUtils.getClassSet(basePackage);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 获取定义类集合
     * @return
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取定义类所有Service集合
     * @return
     */
    public static Set<Class<?>> getServiceClassSet(){
        return CLASS_SET.stream().filter(dto ->dto.isAnnotationPresent(Service.class)).collect(Collectors.toSet());
    }

    /**
     * 获取定义类所有Controller集合
     * @return
     */
    public static Set<Class<?>> getControllerClassSet(){
        return CLASS_SET.stream().filter(dto ->dto.isAnnotationPresent(RestController.class) || dto.isAnnotationPresent(Controller.class)).collect(Collectors.toSet());
    }

    /**
     * 获取定义类所有Controller集合
     * @return
     */
    public static  Set<Class<?>> getBeanClassSet(){
        return Stream.concat(getControllerClassSet().stream(),getServiceClassSet().stream()).collect(Collectors.toSet());
    }

    /**
     * 获取某父类得所有子类
     * @param superClass
     * @return
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        return CLASS_SET.stream().filter(e -> superClass.isAssignableFrom(e) && !superClass.equals(e)).collect(Collectors.toSet());
    }

    /**
     * 获取带有某注解得所有类
     * @param annotation
     * @return
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotation) {
        return CLASS_SET.stream().filter(e -> e.isAnnotationPresent(annotation)).collect(Collectors.toSet());
    }
}

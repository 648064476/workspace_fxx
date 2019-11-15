package com.fxx.handler;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @Description: 参数包装类
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/18 17:15
 * @Version: 1.0
 */
public class MethodParameter {

    private int parameterIndex;

    private Class<?> parameterType;

    private String parameterName;

    private Map<Class<?>,Annotation> parameterAnnotations;

    public MethodParameter(int parameterIndex, Class<?> parameterType, String parameterName, Map<Class<?>, Annotation> parameterAnnotations) {
        this.parameterIndex = parameterIndex;
        this.parameterType = parameterType;
        this.parameterName = parameterName;
        this.parameterAnnotations = parameterAnnotations;
    }

    public int getParameterIndex() {
        return parameterIndex;
    }

    public MethodParameter setParameterIndex(int parameterIndex) {
        this.parameterIndex = parameterIndex;
        return this;
    }

    public Class<?> getParameterType() {
        return parameterType;
    }

    public MethodParameter setParameterType(Class<?> parameterType) {
        this.parameterType = parameterType;
        return this;
    }

    public String getParameterName() {
        return parameterName;
    }

    public MethodParameter setParameterName(String parameterName) {
        this.parameterName = parameterName;
        return this;
    }

    public Map<Class<?>, Annotation> getParameterAnnotations() {
        return parameterAnnotations;
    }

    public MethodParameter setParameterAnnotations(Map<Class<?>, Annotation> parameterAnnotations) {
        this.parameterAnnotations = parameterAnnotations;
        return this;
    }
}

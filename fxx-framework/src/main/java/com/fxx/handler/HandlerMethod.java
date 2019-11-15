package com.fxx.handler;

import java.lang.reflect.Method;

/**
 * @Description: 处理器
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/17 14:47
 * @Version: 1.0
 */
public class HandlerMethod {

    private  Object bean;

    private  Class<?> beanType;

    private  Method method;

    private  MethodParameter[] parameters;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class<?> getBeanType() {
        return beanType;
    }

    public void setBeanType(Class<?> beanType) {
        this.beanType = beanType;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public MethodParameter[] getParameters() {
        return parameters;
    }

    public void setParameters(MethodParameter[] parameters) {
        this.parameters = parameters;
    }

    public HandlerMethod() {
    }

    public HandlerMethod(Object bean, Class<?> beanType, Method method, MethodParameter[] parameters) {
        this.bean = bean;
        this.beanType = beanType;
        this.method = method;
        this.parameters = parameters;
    }
}

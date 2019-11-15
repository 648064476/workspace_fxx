package com.fxx.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 代理链
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/29 15:00
 * @Version: 1.0
 */
public class ProxyChina {

    private  Class<?> targetClass ;

    private  Object targetObject ;

    private  Method targetMethod ;

    private  MethodProxy methodProxy ;

    private  Object[] methodParam ;

    private List<Proxy> proxyList = new ArrayList<>();

    private int proxyIndex = 0;

    private ProxyChina() {
    }

    public ProxyChina(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy, Object[] methodParam, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParam = methodParam;
        this.proxyList = proxyList;
    }

    public Object doProxyChina() throws Throwable {
        Object methodResult;
        if (proxyIndex < proxyList.size()){
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        }else {
            methodResult = methodProxy.invokeSuper(targetObject, methodParam);
        }
        return methodResult;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public Object[] getMethodParam() {
        return methodParam;
    }

    public List<Proxy> getProxyList() {
        return proxyList;
    }

    public int getProxyIndex() {
        return proxyIndex;
    }
}

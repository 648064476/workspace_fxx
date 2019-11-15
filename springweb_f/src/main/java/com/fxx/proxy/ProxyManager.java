package com.fxx.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.List;

/**
 * @Description: 代理管理器
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/29 15:13
 * @Version: 1.0
 */
public class ProxyManager {

    public static <T> T createProxy(final Class<?> targetClass,final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass, (MethodInterceptor) (targetObject, targetMethod, methodParam, methodProxy) -> new ProxyChina(targetClass, targetObject, targetMethod, methodProxy, methodParam, proxyList).doProxyChina());
    }
}

package com.fxx.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @Description: 切面代理
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/29 15:20
 * @Version: 1.0
 */
public abstract class AspectProxy implements Proxy {

    public static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChina proxyChina) throws Throwable {

        Object result = null;

        begin();
        try {
            if (intercept(proxyChina.getTargetClass(),proxyChina.getTargetMethod(),proxyChina.getMethodParam())){
                before();
                result  = proxyChina.doProxyChina();
                after(proxyChina.getTargetClass(), proxyChina.getTargetMethod(), proxyChina.getMethodParam(), result);
            }else {
                result  = proxyChina.doProxyChina();
            }

        }catch (Exception e){
            logger.error("proxy failure ",e);
            error(proxyChina.getTargetClass(),proxyChina.getTargetMethod(),proxyChina.getMethodParam());
            throw e;
        }finally {
            end();
        }
        return result;
    }

    protected abstract void end();

    protected abstract void error(Class<?> targetClass, Method targetMethod, Object[] methodParam);

    protected abstract void after(Class<?> targetClass, Method targetMethod, Object[] methodParam, Object result);

    protected abstract void begin();

    protected abstract void before();

    protected boolean intercept(Class<?> targetClass, Method targetMethod, Object[] methodParam){
        return true;
    }
}

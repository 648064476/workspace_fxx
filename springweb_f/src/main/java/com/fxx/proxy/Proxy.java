package com.fxx.proxy;

/**
 * @Description: 代理注解
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/29 14:59
 * @Version: 1.0
 */
public interface Proxy {

    /**
     * 执行链式代理
     * @param proxyChina
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChina proxyChina) throws Throwable;
}

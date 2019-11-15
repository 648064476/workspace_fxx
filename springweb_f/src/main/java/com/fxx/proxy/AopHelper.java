package com.fxx.proxy;

import com.fxx.helper.BeanHelper;
import com.fxx.helper.ClassHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @Description: 方法拦截助手类
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/29 15:59
 * @Version: 1.0
 */
public class AopHelper {

    private final static Logger logger = LoggerFactory.getLogger(AopHelper.class);


    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            targetMap.forEach((k, v) -> {
                BeanHelper.setBean(k, ProxyManager.createProxy(k, v));
            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("aop 初始化失败" + e);
        }
    }

    /**
     * 通过 注解参数 查询被拦截得类
     *
     * @param aspect
     * @return
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) {
        Set<Class<?>> targetSet = new HashSet<>();
        Class<? extends Annotation> value = aspect.value();
        return value != null && !value.equals(aspect.getClass()) ? ClassHelper.getClassSetByAnnotation(value) : targetSet;
    }

    /**
     * 获取 切面类 和 需要拦截类 关系得集合
     *
     * @return
     * @throws Exception
     */
    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<>();
        addAspectProxy(proxyMap);
        addTransactionProxy(proxyMap);
        return proxyMap;
    }

    /**
     * 添加所有切面类和 被拦截类对应关系
     *
     * @param proxyMap
     */
    private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
        Set<Class<?>> proxyClass = ClassHelper.getClassSetBySuper(AspectProxy.class);
        proxyClass.stream()
                .filter(e -> e.isAnnotationPresent(Aspect.class))
                .forEach(e -> proxyMap.put
                        (e, createTargetClassSet(e.getAnnotation(Aspect.class))));
    }

    /**
     * 添加所有事务 和 service对应关系
     *
     * @param proxyMap
     */
    private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
        Set<Class<?>> serviceClassSet = ClassHelper.getServiceClassSet();
        proxyMap.put(TransactionProxy.class, serviceClassSet);
    }


    /**
     * 获取 被拦截类 对应执行得 拦截方法
     *
     * @return
     * @throws Exception
     */
    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws
            Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();

        proxyMap.forEach((k, v) ->
                v.forEach(clazz -> {
                    try {

                        Proxy proxy = (Proxy) k.newInstance();
                        if (targetMap.containsKey(clazz)) {
                            targetMap.get(clazz).add(proxy);
                        } else {
                            List<Proxy> list = new ArrayList<>();
                            list.add(proxy);
                            targetMap.put(clazz, list);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }));
        return targetMap;
    }
}

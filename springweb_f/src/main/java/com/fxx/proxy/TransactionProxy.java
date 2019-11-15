package com.fxx.proxy;

import com.fxx.annotation.Transaction;
import com.fxx.helper.DatabaseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @Description: 事务代理
 * @Author: FeiXinXin
 * @CreateDate: 2019/6/3 10:21
 * @Version: 1.0
 */
public class TransactionProxy implements Proxy {


    private static final Logger logger = LoggerFactory.getLogger(TransactionProxy.class);

    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return false;
        }
    };


    @Override
    public Object doProxy(ProxyChina proxyChina) throws Throwable {
        Object result = null;
        Boolean flag = FLAG_HOLDER.get();
        Method targetMethod = proxyChina.getTargetMethod();
        if (!flag && targetMethod.isAnnotationPresent(Transaction.class)){
            FLAG_HOLDER.set(true);
            try {
                DatabaseHelper.beginTransaction();
                logger.debug("开始事务");
                result = proxyChina.doProxyChina();
                DatabaseHelper.commitTransaction();
                logger.debug("结束事务");
                logger.debug("提交事务");
            }catch (Exception e){
                DatabaseHelper.rollbackTransaction();
                logger.error("回滚事务");
                throw e;
            }finally {
                FLAG_HOLDER.remove();
            }
        }else{
            result = proxyChina.doProxyChina();
        }

        return result;
    }
}

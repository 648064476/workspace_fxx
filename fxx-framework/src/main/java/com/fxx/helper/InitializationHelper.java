package com.fxx.helper;

import com.fxx.proxy.AopHelper;
import com.fxx.util.ClassUtils;

/**
 * @Description: 初始化工具类
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/20 11:53
 * @Version: 1.0
 */
public class InitializationHelper {

    public static void  init() {
        Class[] classList = {ClassHelper.class,
                BeanHelper.class,
                    AopHelper.class,
                IOCHelper.class};
        for (Class aClass : classList) {
            ClassUtils.loadClass(aClass.getName(), true);
        }
    }
}

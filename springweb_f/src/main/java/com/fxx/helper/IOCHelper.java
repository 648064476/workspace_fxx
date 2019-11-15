package com.fxx.helper;

import com.fxx.annotation.Autowired;
import com.fxx.util.ReflectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: IOC助手类
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/17 14:24
 * @Version: 1.0
 */
public class IOCHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (!Objects.isNull(beanMap)){
            beanMap.forEach((cla,bean)->{
                Field[] fields = cla.getDeclaredFields();
                if (!ArrayUtils.isEmpty(fields)){
                    Arrays.stream(fields).forEach(field ->{
                        //判读是否有Autowired注解
                        if (field.isAnnotationPresent(Autowired.class)){
                            //从容器中获取并设置值
                            Object filedInstance = beanMap.get(field.getType());
                                //通过反射设置field的值
                            if (!Objects.isNull(filedInstance)){
                                ReflectionUtils.setFiled(bean,field,filedInstance);
                            }
                        }
                    });
                }
            });
        }
    }
}

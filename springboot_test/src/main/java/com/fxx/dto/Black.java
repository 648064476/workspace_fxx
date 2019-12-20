package com.fxx.dto;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
public class Black implements Color, BeanNameAware,
        BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {


    public Black () {
        System.out.println("Black 已被加载");
    }

    @Override
    public void setBeanFactory (BeanFactory beanFactory) throws BeansException {
        System.out.println("<" + this.getClass() .getSimpleName() + "】 调用 BeanFactoryAware 的 setBeanFactory");
    }

    @Override
    public void setBeanName (String name) {
        System.out.println("<" + this.getClass() .getSimpleName() + "】 调用 BeanNameAware 的 setBeanName");
    }

    @Override
    public void destroy () throws Exception {
        System.out.println("<" + this.getClass() .getSimpleName() + "】 调用 DisposableBean 的 destroy");
    }

    @Override
    public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
        System.out.println("<" + this.getClass() .getSimpleName() + "】 调用 ApplicationContextAware 的 setApplicationContext");
    }

    @Override
    public void afterPropertiesSet () throws Exception {
        System.out.println("<" + this.getClass() .getSimpleName() + "】 调用 afterPropertiesSet 的 afterPropertiesSet");
    }
}

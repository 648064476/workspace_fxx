package com.fxx.dto;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class Red implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization (Object bean, String beanName) throws BeansException {
        System.out.println("blue  postProcessBeforeInitialization");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization (Object bean, String beanName) throws BeansException {
        System.out.println("blue  postProcessAfterInitialization");
        return null;
    }
}

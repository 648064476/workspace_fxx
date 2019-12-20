package com.fxx.factorybean;

import com.fxx.dto.Apple;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author gantingting
 */
@Component("apple")
public class AppleFactoryBean implements FactoryBean<Apple> {

    public AppleFactoryBean () {
        System.out.println("AppleFactoryBean 被加载");
    }

    @Override
    public Apple getObject () throws Exception {
        System.out.println("AppleFactoryBean");
        Apple apple = new Apple();
        apple.setName("bigApple");
        return apple;
    }

    @Override
    public Class<?> getObjectType () {
        return Apple.class;
    }
}

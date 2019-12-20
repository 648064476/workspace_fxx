package com.fxx.dto;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class Blue{


    @Autowired
    public Blue (BeanFactory appleFactoryBean) {
        try {

            Apple object = (Apple) appleFactoryBean.getBean("apple");
            White white = (White) appleFactoryBean.getBean(White.class);
            System.out.println(white.getDate() +" -- --- "+object);
            Object object1=  appleFactoryBean.getBean("&apple");
            System.out.println(object1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

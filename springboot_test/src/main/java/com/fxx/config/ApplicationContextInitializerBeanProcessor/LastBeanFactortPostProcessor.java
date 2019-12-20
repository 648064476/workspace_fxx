package com.fxx.config.ApplicationContextInitializerBeanProcessor;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author gantingting
 *  *  springboot初始化带入BeanDefinitionRegistryPostProcessor
 */
public class LastBeanFactortPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory (ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("自定义  LastBeanDefinitionRegistryPostProcessor");
    }
}

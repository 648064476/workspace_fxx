package com.fxx.config.ApplicationContextInitializerBeanProcessor;


import com.fxx.dto.Red;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author gantingting
 *  *  springboot初始化带入BeanDefinitionRegistryPostProcessor
 */

public class FirstBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry (BeanDefinitionRegistry registry) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("FirstBeanDefinitionRegistryPostProcessor postProcessBeanDefinitionRegistry  ...bean的数量：" + registry.getBeanDefinitionCount());
        //RootBeanDefinition beanDefinition = new RootBeanDefinition(Blue.class);
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Red.class).getBeanDefinition();
        registry.registerBeanDefinition("red", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory (ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("自定义  FirstBeanDefinitionRegistryPostProcessor");
    }
}

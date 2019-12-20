package com.fxx.config.ApplicationContextInitializerBeanProcessor;


import com.fxx.config.ApplicationContextInitializerBeanProcessor.FirstBeanDefinitionRegistryPostProcessor;
import com.fxx.config.ApplicationContextInitializerBeanProcessor.LastBeanFactortPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author gantingting
 */
public class SpringApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize (ConfigurableApplicationContext applicationContext) {
        FirstBeanDefinitionRegistryPostProcessor firstBeanDefinitionRegistryPostProcessor = new FirstBeanDefinitionRegistryPostProcessor();
        LastBeanFactortPostProcessor lastBeanFactortPostProcessor = new LastBeanFactortPostProcessor();
        // 将自定义的firstBeanDefinitionRegistryPostProcessor添加到应用上下文中
        applicationContext.addBeanFactoryPostProcessor(firstBeanDefinitionRegistryPostProcessor);
        applicationContext.addBeanFactoryPostProcessor(lastBeanFactortPostProcessor);
        // ...自定义操作
        System.out.println("SpringApplicationContextInitializer#initialize");
    }
}


package com.fxx.config.refreshedListener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * IOC容器初始化时回调
 */
public class HelloApplicationContextInitializer  implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize (ConfigurableApplicationContext applicationContext) {
        System.out.println("HelloApplicationContextInitializer");
    }
}

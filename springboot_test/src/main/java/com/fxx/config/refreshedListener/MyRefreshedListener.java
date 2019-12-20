package com.fxx.config.refreshedListener;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author gantingting
 */
@Component
public class MyRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent (ContextRefreshedEvent event) {
        System.out.println("自定义监听器 MyRefreshedListener");
        // 自己的逻辑处理
    }
}


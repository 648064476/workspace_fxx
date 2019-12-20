package com.fxx;


import com.fxx.config.ApplicationContextInitializerBeanProcessor.SpringApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ConcurrentHashMap;


/**
 * @author gantingting
 */
@SpringBootApplication
public class Test {

    public void ApplicationContextInitializer () {
//        加载自定义ApplicationContextInitializer
        SpringApplication application = new SpringApplication(Test.class);
        application.addInitializers(new SpringApplicationContextInitializer());
        application.run(null);
    }

    public static void main (String[] args) {
//        SpringApplication.run(Test.class, args);

        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        objectObjectConcurrentHashMap.put("a", "a");
        System.out.println(objectObjectConcurrentHashMap);
    }
}

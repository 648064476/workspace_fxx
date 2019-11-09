package com.fxx.mq.rocketmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "rocketmq.consumer")
@Configuration
@Data
public class ConsumerProperties {

    private String groupName;

    private String namesrvAddr;
}

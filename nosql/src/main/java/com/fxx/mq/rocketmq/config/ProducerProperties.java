package com.fxx.mq.rocketmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "rocketmq.producer")
@Configuration
@Data
public class ProducerProperties {
    private String namesrvAddr;

    private String groupName;
}

package com.fxx.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxx.config.componetScan.ColorBeanLoadFilter;
import com.fxx.config.conditional.ConditionTest;
import com.fxx.service.ReceiverRedisMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

//@ComponentScan(value = "com.fxx.dto",basePackageClasses = {White.class})
@ComponentScan(basePackages = "com.fxx.dto", includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, value = ColorBeanLoadFilter.class)}, useDefaultFilters = true)
@Configuration
public class ExtConfig {

    //    @Conditional(Orange.class)
//    @ConditionalOnClass(Orange.class)
//    @ConditionalOnBean(Orange.class)
//    @ConditionalOnMissingBean(Orange.class)
//    @ConditionalOnMissingClass(Orange.class)
//    @ConditionalOnProperty(Orange.class)
//    @ConditionalOnResource(Orange.class)
    @Bean
    public ConditionTest orange () {
        return new ConditionTest();
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate (RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    /**
     * 创建连接工厂
     *
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container (RedisConnectionFactory connectionFactory,
                                                    MessageListenerAdapter listenerAdapter, MessageListenerAdapter listenerAdapterTest2) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //接受消息的key
        container.addMessageListener(listenerAdapter, new PatternTopic("phone"));
        container.addMessageListener(listenerAdapterTest2, new PatternTopic("phoneTest2"));
        return container;
    }


    /**
     * 绑定消息监听者和接收监听的方法
     *
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter (ReceiverRedisMessage receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    /**
     * 绑定消息监听者和接收监听的方法
     *
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapterTest2 (ReceiverRedisMessage receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage2");
    }

    /**
     * 注册订阅者
     *
     * @param latch
     * @return
     */
    @Bean
    ReceiverRedisMessage receiver (CountDownLatch latch) {
        return new ReceiverRedisMessage(latch);
    }


    /**
     * 计数器，用来控制线程
     *
     * @return
     */
    @Bean
    public CountDownLatch latch () {
        return new CountDownLatch(1);//指定了计数的次数 1
    }

}

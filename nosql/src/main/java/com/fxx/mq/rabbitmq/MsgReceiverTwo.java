package com.fxx.mq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: FeiXinXin
 * @CreateDate: 2019/7/23 11:14
 * @Version: 1.0
 */
@Component
public class MsgReceiverTwo {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String msg){
        logger.info("receiveTopic1:" + msg);
    }

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String msg){
        logger.info("receiveTopic2:" + msg);
    }
}

package com.fxx.mq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: java类作用描述
 * @Author: FeiXinXin
 * @CreateDate: 2019/7/23 11:13
 * @Version: 1.0
 */
@Component
public class MsgReceiverOne {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    public void process(String content) {
        logger.info("处理器one接收处理队列A当中的消息： " + content);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    public void process1(String content) {
        logger.info("处理器two接收处理队列A当中的消息： " + content);
    }
}

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
public class MsgReceiverThree {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RabbitListener(queues = RabbitConfig.HEADER_QUEUE)
    public void receiveHeaders(byte[] msg){
        System.out.println(msg);
        logger.info("receiveHeaders:" + new String(msg));
    }
}

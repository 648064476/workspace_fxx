package com.fxx.mq;

import com.alibaba.fastjson.JSON;
import com.fxx.mq.kafka.UserLogProducer;
import com.fxx.mq.rabbitmq.MsgProducer;
import com.fxx.mq.rocketmq.config.TestTransactionListener;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * @Author: FeiXinXin
 * @CreateDate: 2019/7/23 11:24
 * @Version: 1.0
 */
@RestController
public class MqController {

    private static final Logger logger = LoggerFactory.getLogger(MqController.class);

    private static final SendCallback callback = new SendCallback() {
        @Override
        public void onSuccess (SendResult sendResult) {
            logger.info("传输成功");
            logger.info(JSON.toJSONString(sendResult));

        }

        @Override
        public void onException (Throwable throwable) {
            logger.error("传输失败", throwable);
        }
    };

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Autowired
    private TransactionMQProducer transactionMQProducer;

    @Autowired
    private TestTransactionListener testTransactionListener;

    @Autowired
    private UserLogProducer kafkaSender;

    @PostMapping("/rabbit/sendMsg")
    public String seadMsg(){
        for (int i = 0;i<=100;i++){
            msgProducer.sendMsg("消息" + i);
        }
        return "完成";
    }

    @PostMapping("/rabbit/sendTopic")
    public String sendTopic(){
        for (int i = 0;i<=2;i++){
            msgProducer.sendTopic("topic.key" + i,"消息"+i);
        }
        return "完成";
    }

    @PostMapping("/rabbit/sendFanout")
    public String sendFanout(){
        for (int i = 0;i<=2;i++){
            msgProducer.sendFanout("消息"+i);
        }
        return "完成";
    }

    @PostMapping("/rabbit/sendHeaders")
    public String sendHeaders(){
        for (int i = 0;i<1;i++){
            msgProducer.sendHeaders("秒杀商品成功"+i);
        }
        return "完成";
    }

    @PostMapping("/rocket/sendMessage")
    public String sendMessage(){
        for (int i = 0;i<1;i++){
            try {
                defaultMQProducer.send(new Message("TopicTest", "Tag1", "12345", ("商品秒杀 ++ " + i).getBytes()),callback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "完成";
    }

    @PostMapping("/rocket/sendMessageTran")
    public String sendMessageTran(){
        for (int i = 0;i<1;i++){
            try {
                transactionMQProducer.setTransactionListener(testTransactionListener);
                transactionMQProducer.send(new Message("TopicTest", "Tag1", "12345", ("商品秒杀 ++ " + i).getBytes()),callback );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "完成";
    }

    @PostMapping("/kafka/send")
    public String send(){
            //调用消息发送类中的消息发送方法
//        new Runnable() {
//            @Override
//            public void run () {
//                ;for (int i = 0; i < 5 ; i++) {
                    kafkaSender.sendLog("我是kafka");
                    kafkaSender.sendLog("你是kafka");
//                }
//            }
//        };
        return "成功";
    }
}

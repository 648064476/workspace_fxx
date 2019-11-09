package com.fxx.mq.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import java.io.UnsupportedEncodingException;

/**
 * 同步发生消息
 */
public class SyncProducer {

    public static void main (String[] args) throws UnsupportedEncodingException {
        DefaultMQProducer producer = new
                DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        //Launch the instance.
        try {
            producer.start();
        for (int i = 0; i < 5; i++) {
            //Create a message instance, specifying topic, tag and message body.
            /* Topic */
            Message msg = new Message("TopicTest" ,

                    /* Tag */
                    "TagA" ,
                    ("SyncProducer " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET)
                    /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = null;

                sendResult = producer.send(msg);

            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

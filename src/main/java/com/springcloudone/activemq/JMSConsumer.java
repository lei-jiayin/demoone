package com.springcloudone.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * 消息消费者
 * @author xw
 * @date 2019/7/24 10:17
 */
@Component
public class JMSConsumer {

/*    @JmsListener(destination = "springboot.queue.test")
    public void receiveQueue(String msg){
        System.out.println("接收到的消息：" + msg);
    }*/

    /**
     * 接收topic消息 并处理
     * @param msg
     */
    @JmsListener(destination = JMSConfig.TOPIC,containerFactory = "jmsListenerContainerTopic")
    public void onTopicMessage(Object msg) {
        System.out.println("topic接收到的消息：" + msg);
    }

    /**
     * 接收queue消息 并处理
     * @param msg
     */
    @JmsListener(destination = JMSConfig.QUEUE,containerFactory = "jmsListenerContainerQueue")
    public void onQueueMessage(Map msg) {
        System.out.println("queue接收到的消息：" + msg);
        Set<String> msgs = msg.keySet();
        for (String key:msgs) {
            Object value = msg.get(key);
            System.out.println(key + ":" + value);
        }
    }

}

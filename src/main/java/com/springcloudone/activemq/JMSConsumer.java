package com.springcloudone.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

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
     * 监听topic消息 并处理
     * @param msg
     */
    @JmsListener(destination = JMSConfig.TOPIC,containerFactory = "jmsListenerContainerTopic")
    public void onTopicMessage(String msg) {
        System.out.println(Thread.currentThread().getName() +" No.1 Topic Receiver : " + msg);
    }

    /**
     * 监听topic消息 并处理
     * @param text
     */
    @JmsListener(destination = JMSConfig.TOPIC, containerFactory="jmsListenerContainerTopic")
    public void onTopicMessageTwo(String text) {
        System.out.println(Thread.currentThread().getName() +" No.2 Topic Receiver : " + text);
    }
    /**
     * 监听queue消息 并处理
     * @param text
     */
    @JmsListener(destination = JMSConfig.QUEUE, containerFactory="jmsListenerContainerQueue")
    public void onQueueMessageTwo(String text) {
        System.out.println(Thread.currentThread().getName() +" No.1 Queue Receiver : " + text);
    }


    /**
     * 监听queue消息 并处理
     * @param msg
     */
   /* @JmsListener(destination = JMSConfig.QUEUE,containerFactory = "jmsListenerContainerQueue")
    public void onQueueMessage(Map msg) {
        System.out.println("queue接收到的消息：" + msg);
        Set<String> msgs = msg.keySet();
        for (String key:msgs) {
            Object value = msg.get(key);
            System.out.println(key + ":" + value);
        }
    }*/

    @JmsListener(destination = JMSConfig.RESPONSE_QUEUE, containerFactory="jmsListenerContainerQueue")
    @SendTo("out.queue")
    public String onResponseMessage(String msg){
        System.out.println("response得到的消息：" + msg);
        return msg;
    }
}

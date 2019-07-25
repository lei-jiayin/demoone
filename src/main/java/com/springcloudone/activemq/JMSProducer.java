package com.springcloudone.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import java.util.Map;

/**
 * 消息生产者
 * @author xw
 * @date 2019/7/24 10:11
 */
@Component
public class JMSProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessageString(Destination destination, String message){
        this.jmsTemplate.convertAndSend(destination,message);
    }

    /**
     * 消息转发或成为直接的消息生产者
     * @param destination
     * @param message
     */
    public void sendMessageMap(Destination destination, Map message){
        this.jmsMessagingTemplate.convertAndSend(destination,message);
    }

    /**
     * 接收P2P模式下 消费者返回的数据
     * @param message
     */
    @JmsListener(destination = "out.queue")
    public void receiveResponse(String message){
        System.out.println("消费者返回的消息：" + message);
    }

}

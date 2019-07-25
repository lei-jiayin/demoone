package com.springcloudone.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * JMS配置
 * 使activeMQ可以接收发送QUEUE TOPIC消息
 * @author xw
 * @date 2019/7/24 11:32
 */
@Configuration
@EnableJms
public class JMSConfig {
    /**
     * 主题消息队列
     */
    public final static String TOPIC = "springboot.topic.test";
    /**
     * 点对点消息队列
     */
    public final static String QUEUE = "springboot.queue.test";
    /**
     * 双向应答消息队列
     */
    public final static String RESPONSE_QUEUE="springboot.response.test";
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(QUEUE);
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(TOPIC);
    }

    @Bean
    public Queue responseQueue(){
        return new ActiveMQQueue(RESPONSE_QUEUE);
    }

    // topic模式的ListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);// 为true时表示是发布订阅模式
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }
    // queue模式的ListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(false);// 为false表示点对点模式
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }
}

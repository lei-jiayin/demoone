package com.springcloudone.demoone;

import com.springcloudone.activemq.JMSProducer;
import com.springcloudone.demoone.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemooneApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JMSProducer jmsProducer;

    @Autowired
    private Topic topic;
    @Autowired
    private Queue queue;


    @Test
    public void httpGet() {
        User user = restTemplate.getForObject("http://localhost:8088/user/1", User.class);
        System.out.println("user = " + user);
    }


    /**
     * 测试activemq的queue消息
     */
    @Test
    public void testJms() {
//        Destination destination = new ActiveMQQueue("springboot.queue.test");

        //构造消息
        Map<String,String> map = new HashMap<>();
        map.put("userName","xiongwei");
        map.put("password","hfisshagua");

        //发送消息
        jmsProducer.sendMessageMap(queue,map);

/*        for (int i=0;i<10;i++) {
            jmsProducer.sendMessage(destination,"hello,world!" + i);
        }*/
    }


    @Test
    public void testJms1() {
        /*for (int i=0;i<10;i++) {
            jmsProducer.sendMessage(queue,"queue,world!" + i);
            jmsProducer.sendMessage(topic, "topic,world!" + i);
        }*/
    }
}

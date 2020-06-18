package com.springcloudone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * kafka 模拟消息发送
 * @author xw
 * @date 2020/6/16 14:45
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public void send(){
        kafkaTemplate.send("mytopic",0,"key","this a messgae");
    }
}

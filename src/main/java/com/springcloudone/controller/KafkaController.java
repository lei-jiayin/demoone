package com.springcloudone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public boolean send(@RequestParam String name){
        /**
         * 创建名为mytopic的Topic，传递的消息是name
         */
        kafkaTemplate.send("mytopic",name);
        return true;
    }
}

package com.springcloudone.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author xw
 * @date 2020/6/16 14:55
 */
@Component
public class ConsumerService {

    @KafkaListener(topics = "mytopic", groupId = "testGroup")
    public void listen(ConsumerRecord<String, String> record){
        System.out.println(record);
        String value = record.value();
        System.out.println(value);
    }
}

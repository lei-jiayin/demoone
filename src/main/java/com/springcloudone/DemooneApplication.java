package com.springcloudone;

import com.springcloudone.netty.webSocket.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.springcloudone.dao")
@ServletComponentScan("com.springcloudone.component.filter")
@EnableScheduling
@EnableCaching //开启缓存注解
public class DemooneApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemooneApplication.class, args);
        try{
            new WebSocketServer().run(8090);
        }catch (Exception e){
            System.out.println("nettyServerError：" + e.getMessage());
        }
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();//HttpUrlConnection
    }
}

package com.springcloudone.demoone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaServer
public class DemooneApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemooneApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();//HttpUrlConnection
    }
}

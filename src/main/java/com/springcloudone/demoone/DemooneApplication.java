package com.springcloudone.demoone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DemooneApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemooneApplication.class, args);
    }

}

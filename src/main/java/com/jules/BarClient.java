package com.jules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BarClient {

    public static void main(String[] args) {
        SpringApplication.run(BarClient.class, args);
    }
}
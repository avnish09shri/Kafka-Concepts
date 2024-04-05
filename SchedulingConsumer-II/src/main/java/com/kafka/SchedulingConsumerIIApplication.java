package com.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SchedulingConsumerIIApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchedulingConsumerIIApplication.class, args);
    }
}

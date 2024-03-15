package com.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JsonHandlingProducerIIApplication {
    public static void main(String[] args) {
        SpringApplication.run(JsonHandlingProducerIIApplication.class, args);
    }
}

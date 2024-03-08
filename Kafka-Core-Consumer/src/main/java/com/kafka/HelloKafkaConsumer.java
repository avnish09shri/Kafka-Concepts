package com.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HelloKafkaConsumer {

    @KafkaListener(topics = "t-hello")
    private void consume(String message){
        System.out.println(message);
    }
}

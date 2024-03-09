package com.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class FixedRateConsumer2 {

    @KafkaListener(topics = "t-fixedrate-2")
    private void consume(String message){
        log.info("consuming message from producer -2: {}", message);
    }
}

package com.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
@Slf4j
public class FixedRateProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    int counter = 1;

    @Scheduled(fixedRate = 1000)
    private void sendMessage(){
       var i = counter++;
       log.info("i is: {}", i);
        kafkaTemplate.send("t-fixedrate", "Fixed rate: ", i+"");
    }
}

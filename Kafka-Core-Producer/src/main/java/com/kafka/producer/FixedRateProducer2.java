package com.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FixedRateProducer2 {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    int counter = 1;

    //@Scheduled(fixedRate = 1000)
    private void sendMessage(){
       var i = counter++;
       log.info("i is: {}", i);
        kafkaTemplate.send("t-fixedrate-2", "Fixed rate of producer -2 : ", i+"");
    }
}

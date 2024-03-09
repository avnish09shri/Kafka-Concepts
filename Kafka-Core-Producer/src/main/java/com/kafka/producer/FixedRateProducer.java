package com.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FixedRateProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    int counter = 1;

    //@Scheduled(fixedRate = 1000) // when we use scheduling we need to enable @enablescheduling in main class
                                        // and disable kafkaproducer instance.
    private void sendMessage(){
       var i = counter++;
       log.info("i is: {}", i);
        kafkaTemplate.send("t-fixedrate", "Fixed rate: ", i+"");
    }
}

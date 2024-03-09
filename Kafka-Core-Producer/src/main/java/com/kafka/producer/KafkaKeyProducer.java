package com.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaKeyProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String key, String value){ //create topic with multiple partitions
        kafkaTemplate.send("t-multi-partitions", key, value);
    }

}

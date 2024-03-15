package com.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommodityProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Commodity commodity) throws JsonProcessingException {
        var commodityData = objectMapper.writeValueAsString(commodity);
        kafkaTemplate.send("t-commodity", commodity.getName() ,commodityData);
    }
}

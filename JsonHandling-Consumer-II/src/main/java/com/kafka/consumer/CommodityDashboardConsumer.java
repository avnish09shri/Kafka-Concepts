package com.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.entity.Commodity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommodityDashboardConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-commodity", groupId = "cg-dashboard")
    public void consumer(String message) throws JsonProcessingException {
        var commodity = objectMapper.readValue(message, Commodity.class);

        //ever if we delay time to consume events it will not halt other consumer group.
        log.info("Dashboard logic for: {}", commodity);
    }
}

package com.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.entity.FoodOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodOrderConsumer {

    private static final int MAX_ORDER_AMOUNT = 7;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-food-order", errorHandler = "myFoodOrderErrorHandler")
    public void consume(String message) throws JsonMappingException, JsonProcessingException {
        var foodOrder = objectMapper.readValue(message, FoodOrder.class);
        if (foodOrder.getAmount() > MAX_ORDER_AMOUNT) {
            throw new IllegalArgumentException("Order amount is too many : " + foodOrder.getAmount());
        }

        log.info("Processing food order : {}", foodOrder);
    }
}

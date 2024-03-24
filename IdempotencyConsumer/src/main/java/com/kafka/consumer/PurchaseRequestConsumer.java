package com.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.kafka.entity.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PurchaseRequestConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("cachePurchaseRequest")
    private Cache<Integer, Boolean> cache;

    private boolean isExistsInCache(int purchaseRequestId){
        return Optional.ofNullable(cache.getIfPresent(purchaseRequestId)).orElse(false);
    }

    @KafkaListener(topics = "t-purchase-request")
    public void consume(String message) throws JsonProcessingException {
        var purchaseRequest = objectMapper.readValue(message, PurchaseRequest.class);
        if(isExistsInCache(purchaseRequest.getId())){
            return;
        }
        log.info("Purchase request: {}", purchaseRequest);
        cache.put(purchaseRequest.getId(), true);
    }
}

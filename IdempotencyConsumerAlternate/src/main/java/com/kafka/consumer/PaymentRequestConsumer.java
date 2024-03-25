package com.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.kafka.entity.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentRequestConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("cachePaymentRequest")
    private Cache<PaymentRequestCacheKey, Boolean> cache;

    private boolean isExistsInCache(PaymentRequestCacheKey key){
        return Optional.ofNullable(cache.getIfPresent(key)).orElse(false);
    }

    @KafkaListener(topics = "t-payment-request")
    public void consume(String message) throws JsonProcessingException {
        var paymentRequest = objectMapper.readValue(message, PaymentRequest.class);
        var cacheKey = new PaymentRequestCacheKey(paymentRequest.getPaymentNumber(), paymentRequest.getAmount(), paymentRequest.getTransactionType());
        if(isExistsInCache(cacheKey)){
            return;
        }
        log.info("Purchase request: {}", paymentRequest);
        cache.put(cacheKey, true);
    }
}

package com.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeneralLedgerConsumer {

    @KafkaListener(topics = "t-general-ledger", id = "general-ledger.one")
    public void consumeOne(String message){
        log.info("Log from consumer one: {}", message);
    }

    @KafkaListener(topics = "t-general-ledger")
    public void consumeTwo(String message){
        log.info("Log from consumer two : {}", message);
    }
}

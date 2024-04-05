package com.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeneralLedgerProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private AtomicInteger counter = new AtomicInteger();

    public void send(String message){
        kafkaTemplate.send("t-general-ledger", message);
    }

    @Scheduled(fixedRate = 1000)
    public void schedule(){
        var message = "Ledger " + counter.incrementAndGet();
        send(message);
    }
}
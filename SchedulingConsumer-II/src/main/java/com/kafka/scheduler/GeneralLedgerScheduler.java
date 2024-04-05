package com.kafka.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeneralLedgerScheduler {

    @Autowired
    private KafkaListenerEndpointRegistry registry; // to pause or stop consumer

    @Scheduled(cron = "40 27 15 * * ?")
    public void stop(){
        log.info("Stopping consumer");
        registry.getListenerContainer("general-ledger.one").pause();
    }

    @Scheduled(cron = "1 29 15 * * ?")
    public void start(){
        log.info("Starting consumer");
        registry.getListenerContainer("general-ledger.one").resume();
    }
}

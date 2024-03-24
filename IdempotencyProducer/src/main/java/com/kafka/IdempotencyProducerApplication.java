package com.kafka;

import com.kafka.entity.PurchaseRequest;
import com.kafka.producer.PurchaseRequestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdempotencyProducerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(IdempotencyProducerApplication.class, args);
    }

    @Autowired
    private PurchaseRequestProducer producer;

    @Override
    public void run(String... args) throws Exception {
        var pr1 = new PurchaseRequest(5551, "PR-First", 991, "USD");
        var pr2 = new PurchaseRequest(5552, "PR-Second", 992, "USD");
        var pr3 = new PurchaseRequest(5553, "PR-Third", 993, "USD");

        producer.send(pr1);
        producer.send(pr2);
        producer.send(pr3);

        producer.send(pr1);
    }
}

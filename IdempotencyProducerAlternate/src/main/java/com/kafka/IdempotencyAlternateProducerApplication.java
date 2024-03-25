package com.kafka;

import com.kafka.entity.PaymentRequest;
import com.kafka.producer.PaymentRequestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdempotencyAlternateProducerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IdempotencyAlternateProducerApplication.class, args);
    }

    @Autowired
    private PaymentRequestProducer producer;

    @Override
    public void run(String... args) throws Exception {
        var p1_alpha = new PaymentRequest("P1", 551, "USD", "Notes", "Budget Reserve");
        var p2_alpha = new PaymentRequest("P2", 551, "USD", "Notes", "Approval Workflow");
        var p3_alpha = new PaymentRequest("P3", 551, "USD", "Notes", "Push Notification");

        var p1_beta = new PaymentRequest("P1", 552, "USD", "Notes", "Budget Reserve");
        var p2_beta = new PaymentRequest("P2", 552, "USD", "Notes", "Approval Workflow");
        var p3_beta = new PaymentRequest("P3", 552, "USD", "Notes", "Push Notification");

        producer.send(p1_alpha);
        producer.send(p2_alpha);
        producer.send(p3_alpha);

        producer.send(p1_beta);
        producer.send(p2_beta);
        producer.send(p3_beta);

        producer.send(p2_alpha);
        producer.send(p2_beta);
    }
}

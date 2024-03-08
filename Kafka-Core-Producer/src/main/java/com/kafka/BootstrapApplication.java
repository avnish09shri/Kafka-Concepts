package com.kafka;

import com.kafka.producer.HelloKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class BootstrapApplication implements CommandLineRunner {

    @Autowired
    private HelloKafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        producer.sendHello("Avnish" + ThreadLocalRandom.current().nextInt());
    }
}

package com.kafka;

import com.kafka.producer.KafkaKeyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling
public class BootstrapApplication implements CommandLineRunner {

    @Autowired
    private KafkaKeyProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //producer.sendHello("Avnish" + ThreadLocalRandom.current().nextInt());

        for(int i=0; i<30;++i){
            String key =  "key-" + (i%4);
            String value = "value " + i + "with key " + key;
            producer.sendMessage(key, value);
        }
    }
}

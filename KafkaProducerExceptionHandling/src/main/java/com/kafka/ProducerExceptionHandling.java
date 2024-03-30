package com.kafka;

import com.kafka.entity.FoodOrder;
import com.kafka.entity.SimpleNumber;
import com.kafka.producer.FoodOrderProducer;
import com.kafka.producer.SimpleNumberProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProducerExceptionHandling implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProducerExceptionHandling.class, args);
    }

    @Autowired
    private FoodOrderProducer foodOrderProducer;

    @Autowired
    private SimpleNumberProducer simpleNumberProducer;

    @Override
    public void run(String... args) throws Exception {
        var chickenOrder = new FoodOrder(3, "Chicken");
        var fishOrder = new FoodOrder(10, "Fish");
        var pizzaOrder = new FoodOrder(5, "Pizza");

        foodOrderProducer.send(chickenOrder);
        foodOrderProducer.send(fishOrder);
        foodOrderProducer.send(pizzaOrder);

        for(int i=100;i<103;++i){
            var num = new SimpleNumber(i);
            simpleNumberProducer.send(num);
        }
    }
}

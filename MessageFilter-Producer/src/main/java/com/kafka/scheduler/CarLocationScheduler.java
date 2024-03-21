package com.kafka.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.entity.CarLocation;
import com.kafka.producer.CarLocationProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CarLocationScheduler {

    private CarLocation carOne;
    private CarLocation carTwo;
    private CarLocation carThree;

    @Autowired
    private CarLocationProducer producer;

    public CarLocationScheduler(){
        var now = System.currentTimeMillis();
        carOne = new CarLocation("car-one", now, 0);
        carTwo = new CarLocation("car-two", now, 110);
        carThree = new CarLocation("car-three", now, 95);

    }

    @Scheduled(fixedDelay = 10000)
    public void generateCarLocation() throws JsonProcessingException {
        var now = System.currentTimeMillis();

        carOne.setTimeStamp(now);
        carTwo.setTimeStamp(now);
        carThree.setTimeStamp(now);

        carOne.setDistance(carOne.getDistance()+1);
        carTwo.setDistance(carTwo.getDistance()-1);
        carThree.setDistance(carThree.getDistance()+1);

        producer.sendLocation(carOne);
        producer.sendLocation(carTwo);
        producer.sendLocation(carThree);

        log.info("Sent: {}", carOne);
        log.info("Sent: {}", carTwo);
        log.info("Sent: {}", carThree);
    }
}

package com.kafka.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.entity.Commodity;
import com.kafka.producer.CommodityProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommodityScheduler {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CommodityProducer commodityProducer;

    @Scheduled(fixedDelay = 5000)
    public void fetchCommodities(){
        List<Commodity> commodities = restTemplate.exchange("http://localhost:8081/commodity/v1", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Commodity>>() {
                }).getBody();

        commodities.forEach(i -> {
            try {
                commodityProducer.sendMessage(i);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

    }
}

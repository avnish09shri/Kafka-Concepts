package com.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory producerFactory(){
        var properties = kafkaProperties.buildProducerProperties();
        properties.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, "60000");

        // we can add more properties as well.

        return new DefaultKafkaProducerFactory(properties);
    }

    public KafkaTemplate kafkaTemplate(){
        return  new KafkaTemplate(producerFactory());
    }
}

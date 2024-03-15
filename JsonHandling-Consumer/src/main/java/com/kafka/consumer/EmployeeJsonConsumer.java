package com.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeJsonConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-employee-2")
    public void consume(String message) throws JsonProcessingException {
        Employee employee = objectMapper.readValue(message, Employee.class);
        log.info(employee.toString());
    }
}

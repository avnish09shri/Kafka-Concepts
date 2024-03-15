package com.kafka;

import com.kafka.entity.Employee;
import com.kafka.producer.EmployeeJson2Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class JsonHandlingProducerApplication implements CommandLineRunner {
    @Autowired
    private EmployeeJson2Producer producer;

    public static void main(String[] args) {
        SpringApplication.run(JsonHandlingProducerApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        for(int i=0;i<5;++i){
            var employee = new Employee("emp id: "+i, "Employee name: "+i, LocalDate.now());
            producer.sendMessage(employee);
        }

    }
}

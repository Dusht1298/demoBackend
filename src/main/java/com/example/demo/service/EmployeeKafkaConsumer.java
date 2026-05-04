package com.example.demo.service;

import com.example.demo.entity.EmployeeEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeKafkaConsumer {

    @KafkaListener(topics = "employee-topic", groupId = "employee-group")
    public void consume(EmployeeEvent event) {
        System.out.println("Received Event: " + event.getEventType());
        System.out.println("Employee: " + event.getEmployee().getName());
    }
}

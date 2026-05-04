package com.example.demo.service;

import com.example.demo.entity.EmployeeEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeKafkaProducer {
    private static final String TOPIC = "employee-topic";

    private final KafkaTemplate<String, EmployeeEvent> kafkaTemplate;

    public EmployeeKafkaProducer(KafkaTemplate<String, EmployeeEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(EmployeeEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}

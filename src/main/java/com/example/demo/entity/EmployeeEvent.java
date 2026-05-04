package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEvent {
    private String eventType; // e.g., "CREATED", "UPDATED", "DELETED"
    private Employee employee;

}

package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeEvent;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeKafkaProducer kafkaProducer;

    private static final String CREATE = "CREATE";
    private static final String UPDATE = "UPDATE";
    private static final String DELETE = "DELETE";

    // ✅ CREATE
    public Employee save(Employee emp) {
        Employee saved = repository.save(emp);

        EmployeeEvent event = new EmployeeEvent();
        event.setEventType(CREATE);
        event.setEmployee(saved);

        kafkaProducer.sendEvent(event);

        return saved;
    }

    // ✅ READ ALL
    public List<Employee> getAll() {
        return repository.findAll();
    }

    // ✅ READ BY ID
    public Optional<Employee> getById(Long id) {
        return repository.findById(id);
    }

    // ✅ UPDATE
    public Employee update(Employee emp) {
        Employee updated = repository.save(emp);

        EmployeeEvent event = new EmployeeEvent();
        event.setEventType(UPDATE);
        event.setEmployee(updated);

        kafkaProducer.sendEvent(event);

        return updated;
    }

    // ✅ DELETE
    public void delete(Long id) {
        Optional<Employee> emp = repository.findById(id);

        if (emp.isPresent()) {
            repository.deleteById(id);

            EmployeeEvent event = new EmployeeEvent();
            event.setEventType(DELETE);
            event.setEmployee(emp.get());

            kafkaProducer.sendEvent(event);
        }
    }
}


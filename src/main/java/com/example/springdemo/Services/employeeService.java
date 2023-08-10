package com.example.springdemo.Services;

import com.example.springdemo.Entities.employee;
import com.example.springdemo.Repositories.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class employeeService {
    @Autowired
    private employeeRepository repository;
    public employee saveEmployee(employee e){
        return repository.save(e);
    }
}

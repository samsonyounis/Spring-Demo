package com.example.springdemo.Controller;

import com.example.springdemo.Entities.employee;
import com.example.springdemo.Services.employeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class employeeController {
    @Autowired
    private employeeService service;

    @PostMapping("/employee/add")
    public employee saveEmployee(@RequestBody @Valid employee e){
        return service.saveEmployee(e);
    }
}

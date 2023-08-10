package com.example.springdemo.Repositories;

import com.example.springdemo.Entities.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepository extends JpaRepository<employee, Long> {
}

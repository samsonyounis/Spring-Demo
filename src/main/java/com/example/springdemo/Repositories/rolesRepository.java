package com.example.springdemo.Repositories;

import com.example.springdemo.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface rolesRepository extends JpaRepository<Roles, Integer> {
}

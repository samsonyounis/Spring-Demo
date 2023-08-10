package com.example.springdemo.Repositories;

import com.example.springdemo.Entities.account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface accountRepo extends JpaRepository<account, Integer> {
    // function to get all the accounts with paging and sorting

    List<account> findBydate(String date, Pageable pageable);
}

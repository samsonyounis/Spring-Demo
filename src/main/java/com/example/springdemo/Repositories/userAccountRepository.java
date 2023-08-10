package com.example.springdemo.Repositories;

import com.example.springdemo.Entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userAccountRepository extends JpaRepository<UserAccount, Integer> {
    // function to get user by username
   UserAccount findUserAccountByUsername(String username);
}

package com.example.springdemo.Services;

import com.example.springdemo.Entities.Roles;
import com.example.springdemo.Entities.UserAccount;
import com.example.springdemo.Entities.account;
import com.example.springdemo.Repositories.accountRepo;
import com.example.springdemo.Repositories.rolesRepository;
import com.example.springdemo.Repositories.userAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class userAccountService {
    @Autowired
    private accountRepo accountRepository;
    @Autowired
    private userAccountRepository userAccountRepo;
    @Autowired
    private rolesRepository rolesRepo;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //function to create account
    public account createAccount(account Account) {
        return accountRepository.save(Account);
    }

    //function to get all accounts
    public List<UserAccount> getAllUsers() {
        return userAccountRepo.findAll();
    }

    //function to get account by Id
    public Optional<account> getAccountById(int Id) {
        return accountRepository.findById(Id);
    }

    //function to update an account
    public void updateAccount() {

    }

    // function to delete an account
    public void deleteAccount(int Id)
    {
        accountRepository.deleteById(Id);
    }

    //function to update an account
    public account updateAccount(int id, account newAccount) {
        account oldAccount = accountRepository.findById(id).get();
        oldAccount.setUsername(newAccount.getUsername());
        accountRepository.save(oldAccount);
        return oldAccount;
    }



    // function to get all the accounts with paging and sorting
    public List<account> findBydate(String date, PageRequest pageable) {
        return accountRepository.findBydate(date, pageable);
    }

    public Roles addRole(Roles role){
        return rolesRepo.save(role);
    }
    public UserAccount addUser(UserAccount user){
        String encodedPassword = passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userAccountRepo.save(user);
    }

    public List<Roles> getUserByname(String username){
        UserAccount user = userAccountRepo.findUserAccountByUsername(username);
        return user.getUserRoles();
    }
}

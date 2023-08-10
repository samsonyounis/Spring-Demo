package com.example.springdemo.SecurityCofiguration;

import com.example.springdemo.Entities.UserAccount;
import com.example.springdemo.Repositories.userAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class userDetailsService implements UserDetailsService{
    @Autowired
    private userAccountRepository myuserAccountRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = myuserAccountRepo.findUserAccountByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return new  userDetailsClass(user);
    }

}

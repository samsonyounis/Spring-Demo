package com.example.springdemo.SecurityCofiguration;

import com.example.springdemo.Entities.Roles;
import com.example.springdemo.Entities.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import java.util.stream.Collectors;

public class userDetailsClass implements UserDetails {
    private UserAccount user;
    public userDetailsClass(UserAccount user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities1 = this.user.getUserRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRoleName()))
                .collect(Collectors.toList());

        return authorities1;//;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

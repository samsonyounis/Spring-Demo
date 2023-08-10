package com.example.springdemo.Entities;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.util.List;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private List<Roles> userRoles;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Roles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Roles> userRoles) {
        this.userRoles = userRoles;
    }
}

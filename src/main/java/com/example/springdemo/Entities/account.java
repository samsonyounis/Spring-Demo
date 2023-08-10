package com.example.springdemo.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class account {
    @Id
    @Column(name = "UserId")
    private int userID;
    @Column(name = "Username", length = 30,unique = true)
    private String username;
    @Column(name = "Password", length = 10)
    private String password;

    @Column(name = "Date", length = 10)
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {return password;}
}

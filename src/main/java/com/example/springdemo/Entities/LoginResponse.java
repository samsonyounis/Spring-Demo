package com.example.springdemo.Entities;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private String jwtToken;

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public LoginResponse(String jwt) {
        this.jwtToken = jwt;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}

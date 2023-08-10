package com.example.springdemo.Exceptions;

public class NoSuchUserExistsException extends RuntimeException{
    private String messg;
    public NoSuchUserExistsException(){}
    public NoSuchUserExistsException(String msg){
        super(msg);
        this.messg = msg;
    }

    public String getMessg() {
        return messg;
    }

    public void setMessg(String messg) {
        this.messg = messg;
    }
}

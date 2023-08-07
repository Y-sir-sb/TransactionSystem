package com.example.TransactionSystem.empty;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private Object status;
    private String message;
    private String data;
    private String CreatToken;
    public Message(String message){
        this.message = message;
    }
    public Message(boolean status,String CreatToken){
        this.status = status;
        this.CreatToken = CreatToken;
    }
}

package com.example.TransactionSystem.empty;

import java.io.Serializable;

public class CreatTokenMessage implements Serializable{
        private boolean status;
        private String CreatToken;
        public CreatTokenMessage(boolean status,String CreatToken) {
            this.status = status;
            this.CreatToken =CreatToken;
        }
    public CreatTokenMessage(String CreatToken) {
        this.CreatToken =CreatToken;
    }
        public String CreatToken(){return CreatToken;}
    }

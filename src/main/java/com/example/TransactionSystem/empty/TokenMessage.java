package com.example.TransactionSystem.empty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.ibatis.javassist.bytecode.analysis.ControlFlow;

import java.io.Serializable;
@Data
public class TokenMessage implements Serializable {
    private boolean status;
    private String message;
    private String CreatToken;
    private String PreysToken;
    private int onlinecount;
    public TokenMessage() {
    }

    public String getPreysToken() {
        return PreysToken;
    }

    public void setPreysToken(String preysToken) {
        PreysToken = preysToken;
    }

    @JsonCreator
    public TokenMessage(@JsonProperty("PreysToken")String PreysToken,@JsonProperty("onlinecount")int onlinecount) {
        this.PreysToken = PreysToken;
        this.onlinecount = onlinecount;
    }
    public TokenMessage(Boolean status,String preysToken){
        this.PreysToken = preysToken;
        this.status = status;
    }
    public TokenMessage(boolean status, String message) {
        this.status = status;
        this.message = message;

    }
    public TokenMessage(boolean status, String message, String CreatToken) {
        this.status = status;
        this.message = message;
        this.CreatToken = CreatToken;
    }


    public TokenMessage(boolean status, String message,String PreysToken,int onlinecount) {
        this.status = status;
        this.message = message;
        this.PreysToken = PreysToken;
        this.onlinecount = onlinecount;
    }

    public boolean isSuccess() {
        return status;
    }

    public String getParsedToken() {
        return PreysToken;
    }
    public  int onlinecount(){return onlinecount;}

}







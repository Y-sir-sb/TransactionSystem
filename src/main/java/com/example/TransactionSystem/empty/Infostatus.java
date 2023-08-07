package com.example.TransactionSystem.empty;

import java.io.Serializable;

public class Infostatus implements Serializable {
    private Object status;
    private Object usecount;
    public Infostatus(boolean status,int usecount){
        this.status = status;
        this.usecount = usecount;
    }
    public Infostatus(int usecount){
        this.usecount = usecount;
    }
}

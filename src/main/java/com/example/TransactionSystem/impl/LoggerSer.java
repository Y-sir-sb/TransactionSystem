package com.example.TransactionSystem.impl;

import java.util.logging.Logger;

public class LoggerSer {
    Logger logger = Logger.getLogger(LoggerSer.class.getName());
    public Logger log(String username){
        logger.severe(username+"登录系统");
        return logger ;
    }
}

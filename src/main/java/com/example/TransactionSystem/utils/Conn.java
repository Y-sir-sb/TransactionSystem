package com.example.TransactionSystem.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    public  Connection getconnection(){
        String URL= "jdbc:mysql://127.0.0.1:3306";// URL里面有数据库名把
        String Drive = "com.mysql.cj.jdbc.Driver";
        String use = "root";
        final  String pass = "root";
        Connection connection = null;

    try {
        Class.forName(Drive);
        connection = DriverManager.getConnection(URL,use,pass);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (
    SQLException e) {
        throw new RuntimeException(e);
    }
       return connection;
}

}
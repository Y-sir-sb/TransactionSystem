package com.example.TransactionSystem.interenface;
import com.example.TransactionSystem.empty.User;

import java.sql.SQLException;
import java.util.List;

public interface DML {

    boolean isUserExist(String use_name, String actualName, String idCard) throws SQLException;

    void insertUserAll(User user) throws SQLException;

    void deletes(int use_id) throws SQLException;

    List<User> selectUserAll(String use_ActualName) throws SQLException;

    int selectStatusAllonline() throws SQLException;

    //判断用户名密码是否一致
    boolean checkUserCredentials(String username, String password);

    boolean UserStatusUpdater(String use_name,int status) throws SQLException;
}

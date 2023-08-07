package com.example.TransactionSystem.impl;

import com.example.TransactionSystem.empty.User;
import com.example.TransactionSystem.interenface.DML;
import com.example.TransactionSystem.utils.Conn;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class DMLSql implements DML {
    Conn conn = new Conn();
    PreparedStatement statement;
    ResultSet resultSet;
    Statement stat;
    @Override
    public boolean isUserExist(String use_name, String actualName, String idCard) throws SQLException {
        // 执行查询操作，检查是否存在已经注册过的用户具有相同的真实姓名和身份证号
        String sql = "SELECT COUNT(*) FROM haplanet.use_table WHERE use_ActualName = ? AND use_ipcard = ? AND use_name =?";
        try (PreparedStatement statement = conn.getconnection().prepareStatement(sql)) {
            statement.setString(1, use_name);
            statement.setString(2,actualName);
            statement.setString(3,idCard);
            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void insertUserAll(User user) throws SQLException {
        String salt = UUID.randomUUID().toString();
        String sha1HexPassword = DigestUtils.sha256Hex(user.getUse_password());
        String use_Password = salt + sha1HexPassword;
        System.out.print(use_Password);
        String useSql = "INSERT INTO `haplanet`.`use_table` (`use_name`, `use_password`,`use_ActualName`, `use_age`, " +
                "`use_address`, `use_email`, `use_ipcard`, `use_time`,`use_sex`) VALUES (?,?,?,?,?,?,?,?,?)";
        String statuSql = "INSERT INTO `haplanet`.`user_status` (`use_name`,`created_at`) VALUES (?,?)";
        PreparedStatement statement = conn.getconnection().prepareStatement(useSql);
        statement.setString(1, user.getUse_name());
        statement.setString(2, use_Password);
        statement.setString(3, user.getUse_ActualName());
        statement.setString(4, user.getUse_age());
        statement.setString(5, user.getUse_address());
        statement.setString(6, user.getUse_email());
        statement.setString(7, user.getUse_ipcard());
        statement.setString(8,user.getUse_time());
        statement.setString(9,user.getUse_sex());
        PreparedStatement statement_status = conn.getconnection().prepareStatement(statuSql);
        statement_status.setString(1,user.getUse_name());
        statement_status.setString(2,user.getUse_time());
        int num = statement.executeUpdate();
        int num_status = statement_status.executeUpdate();
        System.out.print(num_status > 0 || num > 0 ? "成功" : "失败");
        statement.close();
        statement_status.close();
    }
    @Override
    public void deletes(int use_id) throws SQLException {

        String de_sql = "DELETE FROM `haplanet`.`use_table` WHERE use_id =' " + use_id+ " ' ";
        stat = conn.getconnection().createStatement();

        if(stat.executeUpdate(de_sql) > 0) {
            System.out.print("删除数据成功");
        }else {
            System.out.print("数据不存在，无法删除数据！");
        }
        stat.close();
    }
    @Override
    public List<User> selectUserAll(String use_ActualName) throws SQLException {
        Connection cone = null;
        List<User> use_table = new ArrayList();
        try {
            System.out.print("建立连接数据库通道" + "\n");
            System.out.print("###################################"+"\n");
            System.out.print("\n");
            cone = conn.getconnection();
            System.out.print("数据库连接通道建立成功" + "\n");
            System.out.print("生成查询语句" + "\n");
            String se_sql = "SELECT * FROM haplanet.use_table WHERE use_ActualName = ? ";
            statement = cone.prepareStatement(se_sql);
            statement.setString(1,use_ActualName);
            resultSet = statement.executeQuery();
            System.out.print("执行查询语句............"+"\n");
            while (resultSet.next()) {
                int id = resultSet.getInt("use_id");
                String name = resultSet.getString("use_name");
                String idcard = resultSet.getString("use_ipcard");
                String email = resultSet.getString("use_email");
                String password = resultSet.getString("use_password");
                String topimg = resultSet.getString("use_topimg");
                String ActualName = resultSet.getString("use_ActualName");
                String  age = resultSet.getString("use_age");
                String  address = resultSet.getString("use_address");
                String  uid = resultSet.getString("use_uid");
                String  time = resultSet.getString("use_time");
                String  sex = resultSet.getString("use_sex");

                User us = new User(id,name,password,ActualName,age,address,uid,email,idcard,time,sex);
                use_table.add(us);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询异常---------------以抛出");
        } finally {
        statement.close();
        }
        return use_table;
    }
    @Override
    public int selectStatusAllonline() throws SQLException {
        String query = "SELECT COUNT(*) as row_count FROM haplanet.user_status WHERE status = 1";
        statement = conn.getconnection().prepareStatement(query);
        resultSet = statement.executeQuery();
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt("row_count");
        }
        return count;
    }

    @Override
    public boolean checkUserCredentials(String username, String password) {
        //判断用户名密码是否一致
        String checkUserCredentialSql = "SELECT use_password FROM haplanet.use_table WHERE use_name = ?";

        try (PreparedStatement statement = conn.getconnection().prepareStatement(checkUserCredentialSql)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("use_password");
                    // 对提供的密码进行相同的哈希操作
                    String salt = hashedPassword.substring(0, 36); // 提取盐值
                    String sha256HexPassword = DigestUtils.sha256Hex(password);
                    String hashedInputPassword = salt + sha256HexPassword;
                    // 比较数据库中的哈希密码和输入密码的哈希结果
                    if (hashedPassword.equals(hashedInputPassword)) {
                        return true; // 用户凭据有效
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // 用户凭据无效
    }

    @Override
    public boolean UserStatusUpdater(String use_name,int status) throws SQLException {
        String sql = "UPDATE haplanet.user_status SET status = ?, updated_at = CURRENT_TIMESTAMP WHERE use_name = ?";
        try (PreparedStatement statement = conn.getconnection().prepareStatement(sql)) {
            statement.setInt(1, status);
            statement.setString(2,use_name);

            int rows = statement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }



}


























package com.example.TransactionSystem.controller;
import com.example.TransactionSystem.empty.*;
import com.example.TransactionSystem.impl.DMLSql;
import com.example.TransactionSystem.impl.TokenBean;
import com.example.TransactionSystem.interenface.DML;
import com.example.TransactionSystem.interenface.isTokens;
import com.example.TransactionSystem.utils.WithdrawUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.spi.ToolProvider;

@RestController
@RequestMapping("/api")
public class ApiController {

    DML dml = new DMLSql();
    isTokens tokenBean = new TokenBean();
    @RequestMapping(value = "/Page/regedit", method = RequestMethod.POST)
    public ResponseEntity<Object> regedit(@RequestBody User user) {
        if (user != null) {
            try {
                // 检查是否存在相同的真实姓名和身份证号的用户
                boolean userExists = dml.isUserExist(user.getUse_name(), user.getUse_ActualName(), user.getUse_ipcard());
                if (userExists) {
                    return new ResponseEntity<>(new Message("user_exists"), HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    // 执行插入操作
                    dml.insertUserAll(user);
                    return new ResponseEntity<>(new Message(true,null), HttpStatus.OK);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return new ResponseEntity<>(new Message("failure"), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(new Message("failure"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody User user)
            throws SQLException,NoSuchAlgorithmException {
        String username = user.getUse_name();
        String password = user.getUse_password();

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            // 请求体为空
            return new ResponseEntity<>(new Message("invalid_credentials"), HttpStatus.UNAUTHORIZED);
        }

        boolean userExists = dml.checkUserCredentials(username, password);
        if (userExists) {
            // 更新用户状态
            boolean statusUpdate = dml.UserStatusUpdater(username, 1);
            if (statusUpdate) {
                // 生成令牌
                String  token  = tokenBean.generateToken(user.getUse_name());
                if (token != null) {
//                        //发送token到cookie到响应头
//                        Cookie tokenCookie = new Cookie("token", token);
//                        tokenCookie.setHttpOnly(false);
//                        tokenCookie.setSecure(false); // 仅在使用 HTTPS 时发送
//                        // 设置 SameSite 属性
//                        String sameSiteValue = "Lax"; // 或"Strict"
//                        response.setHeader("Set-Cookie", String.format("%s; SameSite=%s", tokenCookie.toString(), sameSiteValue));
//                        // 添加 Cookie 到响应头
//                        response.addCookie(tokenCookie);
//                        // 返回登录成功的响应，包含令牌
                        return new ResponseEntity<>(new TokenMessage(true,"；令牌成功生成",token), HttpStatus.OK);
                    }
                } else {
                    // 令牌生成失败
                    return new ResponseEntity<>(new Message("token_generation_failed"), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                // 判断数据库状态表是否更新成功，没有成功就返回此结果
                System.out.print("数据库更新失败");
                return new ResponseEntity<>(new Message("status_update_failed"), HttpStatus.UNAUTHORIZED);
            }

        return new ResponseEntity<>(new Message("loginSuess"), HttpStatus.UNAUTHORIZED);

        }
    @RequestMapping(value = "/Page/loginRequest",method = RequestMethod.POST)
    public ResponseEntity<Object>LoginRequest(@RequestBody TokenMessage CleanToken){
        return null;
    }
    @RequestMapping(value = "/Page/HomePage", method = RequestMethod.POST)
    public ResponseEntity<Object> HomePage(@RequestBody TokenMessage token) throws SQLException {
        String preysToken = token.getPreysToken();
        String subValue = tokenBean.parseToken(preysToken);  // 使用 parseToken 方法获取解析后的 sub 值

        if (subValue != null) {
            int count = dml.selectStatusAllonline();
            TokenMessage responseToken = new TokenMessage(true, "token_parse_OK", subValue, count);
            return new ResponseEntity<>(responseToken, HttpStatus.OK);
        } else {
            // 未找到带有 Token 的 Cookie
            return new ResponseEntity<>(new Message("token_not_found"), HttpStatus.UNAUTHORIZED);
        }
    }


}







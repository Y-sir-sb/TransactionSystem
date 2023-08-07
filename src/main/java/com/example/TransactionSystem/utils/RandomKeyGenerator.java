package com.example.TransactionSystem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
//生成密钥令牌随机
public class RandomKeyGenerator {

    private KeyPair keyPair; // 成员变量用于保存生成的密钥对
    public RandomKeyGenerator() {
        // 初始化 keyPair 对象
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            this.keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public PrivateKey privateKey() {
        return keyPair.getPrivate();
    }

    public PublicKey publicKey() {
        return keyPair.getPublic();
    }

    public Timestamp getExpirationTime() {
        // 令牌有效期为2小时
        Timestamp expirationTime = Timestamp.valueOf(LocalDateTime.now().plusHours(2));
        return expirationTime;
    }
}

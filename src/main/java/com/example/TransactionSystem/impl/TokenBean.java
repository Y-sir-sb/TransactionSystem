package com.example.TransactionSystem.impl;

import com.example.TransactionSystem.empty.CreatTokenMessage;
import com.example.TransactionSystem.empty.TokenMessage;
import com.example.TransactionSystem.interenface.isTokens;
import com.example.TransactionSystem.utils.RandomKeyGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.*;
import java.util.ArrayList;
import java.util.Date;

@Component
public class TokenBean implements isTokens {
    RandomKeyGenerator randomKeyGenerator= new RandomKeyGenerator();
    @Override
    public String generateToken(String username) {

        //生成令牌
        try {
            PrivateKey privateKey = randomKeyGenerator.privateKey(); // 获取保存的私钥
            String token = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(randomKeyGenerator.getExpirationTime())
                    .signWith(SignatureAlgorithm.RS512, privateKey)
                    .compact();

            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public String parseToken(String token) {
        try {
            PublicKey publicKey = randomKeyGenerator.publicKey(); // 获取保存的公钥
            Claims claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();  // 返回解析后的 sub 值
        } catch (JwtException e) {
            // 令牌无效或解析失败
            return null;
        }
    }



}

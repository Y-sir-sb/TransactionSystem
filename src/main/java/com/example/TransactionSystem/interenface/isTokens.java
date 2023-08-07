package com.example.TransactionSystem.interenface;

import com.example.TransactionSystem.empty.CreatTokenMessage;
import com.example.TransactionSystem.empty.TokenMessage;

public interface isTokens {
    String  generateToken(String username);

    String  parseToken(String token);
}

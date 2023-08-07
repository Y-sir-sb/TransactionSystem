package com.example.TransactionSystem.utils;

public class WithdrawUtil {
    public String extractSubValue(String preysToken) {
        int subStartIndex = preysToken.indexOf("sub=");  // 查找 sub= 的起始位置
        if (subStartIndex != -1) {
            int subEndIndex = preysToken.indexOf(",", subStartIndex);  // 查找逗号的位置，表示 sub 结束
            if (subEndIndex != -1) {
                return preysToken.substring(subStartIndex + 4, subEndIndex).trim();  // 提取 sub 值并去除首尾空格
            }
        }
        return null;  // 提取失败，返回 null
    }
}

package com.back_end_project.back_end_project.controllerComponentJWT.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.back_end_project.back_end_project.database.Customer;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenService {

    private final Key SECRET_KEY;

    public JwtTokenService(@Value("${jwt.secret}") String secret) {
        this.SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes()); // 從配置中讀取密鑰並轉換為 Key
    }

    private final long EXPIRATION_TIME = 86400000; // Token 有效期 (1 天)

    /**
     * 生成 JWT Token
     *
     * @param customer 客戶資料
     * @return 生成的 Token
     */
    public String generateToken(Customer customer) {
        return Jwts.builder()
                .setSubject(customer.getEmail()) // 主體設為客戶 Email
                .claim("role", customer.getIsSuperAdmin() ? "ADMIN" : "USER") // 添加自定義聲明
                .setIssuedAt(new Date()) // Token 簽發時間
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Token 過期時間
                .signWith(SECRET_KEY) // 使用生成的密鑰進行簽名
                .compact();
    }
}

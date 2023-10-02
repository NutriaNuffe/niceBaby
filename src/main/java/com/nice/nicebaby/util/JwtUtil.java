package com.nice.nicebaby.util;

import com.nice.nicebaby.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        Integer expires_sec = 1 * 60 * 60; //1H
        Date expires = Date.from(LocalDateTime.now().plusSeconds(expires_sec).atZone(ZoneId.systemDefault()).toInstant());
        SecretKey key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());
        return Jwts.builder()
                .setSubject(String.valueOf(user.getUserId()))
                .claim("userId", user.getUserId())
                .claim("account", user.getAccount())
                .setExpiration(expires)
                .signWith(key)
                .compact();
    }

}

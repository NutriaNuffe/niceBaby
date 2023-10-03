package com.nice.nicebaby.util;

import com.nice.nicebaby.entity.User;
import com.nice.nicebaby.exception.JWTExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil {

    private final static Logger log = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        Integer expires_sec = 1 * 60 * 60;
        Date expires = Date.from(LocalDateTime.now().plusSeconds(expires_sec).atZone(ZoneId.systemDefault()).toInstant());
        SecretKey key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());
        return Jwts.builder()
                .claim("userId", user.getUser_id())
                .claim("account", user.getAccount())
                .claim("firstName", user.getFirst_name())
                .claim("sex", user.getSex())
                .claim("phone", user.getPhone())
                .claim("title", user.getTitle())
                .claim("department", user.getDepartment())
                .claim("address", user.getAddress())
                .claim("avatar", user.getAvatar())
                .setExpiration(expires)
                .signWith(key)
                .compact();
    }

    public User verifierToken(String token) {
        try {
            SecretKey key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            User user = new User()
                    .setUser_id(claims.get("userId", Long.class))
                    .setAccount(claims.get("account", String.class))
                    .setFirst_name(claims.get("firstName", String.class))
                    .setSex(claims.get("sex", Integer.class))
                    .setPhone(claims.get("phone", String.class))
                    .setTitle(claims.get("title", Integer.class))
                    .setDepartment(claims.get("department", Integer.class))
                    .setAddress(claims.get("address", String.class))
                    .setAvatar(claims.get("avatar", String.class));
            return user;
        } catch (ExpiredJwtException e) {
            throw new JWTExpiredException();
        } catch (Throwable e) {
            log.error("parserToken error!", e);
            return null;
        }
    }

}

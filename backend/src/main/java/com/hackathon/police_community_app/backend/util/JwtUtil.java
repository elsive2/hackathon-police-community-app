package com.hackathon.police_community_app.backend.util;

import com.hackathon.police_community_app.backend.entity.User;
import com.hackathon.police_community_app.backend.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${app.jjwt.secret}")
    private String secret;

    @Value("${app.jjwt.expiration}")
    private String expirationTime;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getSubjectFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    public UserDetails extractUserInfo(String token) {
        Claims claims = getAllClaimsFromToken(token);

        User user = new User();
        user.setId((Long) claims.get("id"))
                .setRole(Role.valueOf((String) claims.get("role")))
                .setPhoneNumber(claims.getSubject());

        return user;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(Long id, String phone, Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("role", role.name());
        return doGenerateToken(claims, phone);
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        long expirationTimeLong = Long.parseLong(expirationTime); //in seconds
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(createdDate)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public Boolean isValidToken(String token) {
        return !isTokenExpired(token);
    }

    public Long getIdFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return ((Integer) claims.get("id")).longValue();
    }

    public String getRoleFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get("role");
    }
}


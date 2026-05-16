package com.quy.common.util.jwt;

import com.quy.common.security.ERole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final Key key;
    private final long accessTokenExp;
    private final long refreshTokenExp;

    public JwtUtil(JwtProperties props) {
        String secret = props.getSecret();
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException(
                "JWT secret is not configured. Set JWT_SECRET env or configure jwt.secret in application.yml");
        }

        byte[] keyBytes;
        try {
            keyBytes = Base64.getDecoder().decode(secret);
        } catch (IllegalArgumentException ex) {
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        }

        this.key = Keys.hmacShaKeyFor(keyBytes);

        this.accessTokenExp = props.getAccessExp();
        this.refreshTokenExp = props.getRefreshExp();
    }

    public boolean isTokenExpired(String token) {
        try {
            return parseToken(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String generateAccessToken(UUID userId, String username, List<ERole> roles) {
        return Jwts.builder()
            .setSubject(username)
            .claim("roles", roles)
            .claim("userId", userId)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + accessTokenExp))
            .signWith(key)
            .compact();
    }

    public String generateRefreshToken(UUID userId, String username, List<ERole> roles) {
        return Jwts.builder()
            .setSubject(username)
            .claim("roles", roles)
            .claim("userId", userId)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExp))
            .signWith(key)
            .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public UUID extractUserId(String token) {
        Object userIdObj = parseToken(token).get("userId");
        if (userIdObj != null) {
            return UUID.fromString(userIdObj.toString());
        }
        return null;
    }

    public List<ERole> extractRoles(String token) {
        List<?> roles = parseToken(token).get("roles", List.class);
        if (roles != null) {
            return roles.stream()
                .map(role -> ERole.valueOf(role.toString()))
                .collect(Collectors.toList());
        }
        return null;
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}

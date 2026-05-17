package com.quy.common.util.jwt;

import com.quy.common.core.security.ERole;
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
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final Key key;

    @Getter
    private final long accessTokenExp;

    @Getter
    private final long refreshTokenExp;

    private static final String CLAIM_TYPE = "type";
    private static final String TYPE_ACCESS = "ACCESS";
    private static final String TYPE_REFRESH = "REFRESH";

    public JwtUtil(JwtProperties props) {
        String secret = props.getSecret();
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException(
                "JWT secret chưa được cấu hình. Thiết lập JWT_SECRET hoặc jwt.secret trong application.yml");
        }
        byte[] keyBytes;
        try {
            keyBytes = Base64.getDecoder().decode(secret);
        } catch (IllegalArgumentException ex) {
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        }
        this.key             = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExp  = props.getAccessExp();
        this.refreshTokenExp = props.getRefreshExp();
    }

    public String generateAccessToken(UUID userId, String username, List<ERole> roles) {
        return Jwts.builder()
            .setSubject(username)
            .claim("roles", roles)
            .claim("userId", userId)
            .claim(CLAIM_TYPE, TYPE_ACCESS)
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
            .claim(CLAIM_TYPE, TYPE_REFRESH)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExp))
            .signWith(key)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAccessToken(String token) {
        try {
            String type = parseToken(token).get(CLAIM_TYPE, String.class);
            return TYPE_ACCESS.equals(type);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRefreshToken(String token) {
        try {
            String type = parseToken(token).get(CLAIM_TYPE, String.class);
            return TYPE_REFRESH.equals(type);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            return parseToken(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public String extractUsername(String token) {
        return parseToken(token).getSubject();
    }

    public UUID extractUserId(String token) {
        Object userIdObj = parseToken(token).get("userId");
        return userIdObj != null ? UUID.fromString(userIdObj.toString()) : null;
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
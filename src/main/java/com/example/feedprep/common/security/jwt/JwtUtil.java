package com.example.feedprep.common.security.jwt;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import com.example.feedprep.common.constants.BusinessRuleConstants;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.example.feedprep.common.constants.BusinessRuleConstants.BEARER_PREFIX;
import static com.example.feedprep.common.constants.BusinessRuleConstants.REFRESH_TOKEN_EXPIRATION_TIME;

@Component
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String secretKey;

    private SecretKey key;
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + BusinessRuleConstants.ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email) // 토큰 주체를 이메일로 설정
                .setIssuedAt(new Date()) // 발급 시각
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME)) // 만료 시각
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new CustomException(ErrorCode.UNSUPPORTED_TOKEN);
        } catch (MalformedJwtException | JwtException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

    // 토큰 앞의 "Bearer " 제거
    public String substringToken(String tokenValue) {
        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
            return tokenValue.substring(BEARER_PREFIX.length());
        }
        throw new CustomException(ErrorCode.MISSING_TOKEN);
    }

    // 남은 기간 반환 메서드
    public long getRemainingMillis(Claims claims) {
        long expirationTime = claims.getExpiration().getTime();
        long now = System.currentTimeMillis();
        return expirationTime - now;
    }
}
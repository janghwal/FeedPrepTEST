package com.example.feedprep.common.security.jwt;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.common.security.service.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final TokenBlacklistService tokenBlacklistService;

    private static final Set<String> WHITELIST = Set.of(
            "/auth/signup",
            "/auth/login",
            "/admin/signup",
            "/admin/login"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        log.info("Request URI: {}", path);

        if (WHITELIST.stream().anyMatch(path::startsWith)) {
            log.info("JWT 검증 스킵: {}", path);
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String token = authHeader.substring(7);

        if (tokenBlacklistService.isTokenBlacklisted(token)) {
            log.warn("블랙리스트에 등록된 토큰 : {}", token);
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        Claims claims = jwtUtil.validateToken(token);

        if (claims == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String username = claims.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}
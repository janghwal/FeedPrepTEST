package com.example.feedprep.domain.auth.service;

import com.example.feedprep.domain.auth.dto.*;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Set;

public interface AuthService {
    SignupResponseDto signup(SignupRequestDto requestDto);

    TokenResponseDto login(LoginRequestDto requestDto, Set<String> allowedRoles);

    SignupResponseDto adminSignup(AdminSignupRequestDto requestDto);

    void logout(String authHeader, Long userId);

    void withdraw(String authHeader, Long userId);
}

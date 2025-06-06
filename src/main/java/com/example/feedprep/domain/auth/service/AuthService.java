package com.example.feedprep.domain.auth.service;

import com.example.feedprep.domain.auth.dto.*;

import java.util.Set;

public interface AuthService {
    SignupResponseDto signup(SignupRequestDto requestDto);

    TokenResponseDto login(LoginRequestDto requestDto, Set<String> admin);

    SignupResponseDto adminSignup(AdminSignupRequestDto requestDto);

    void logout(String authHeader);

}

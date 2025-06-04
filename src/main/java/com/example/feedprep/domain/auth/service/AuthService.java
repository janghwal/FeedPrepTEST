package com.example.feedprep.domain.auth.service;

import com.example.feedprep.domain.auth.dto.AdminSignupRequestDto;
import com.example.feedprep.domain.auth.dto.SignupRequestDto;
import com.example.feedprep.domain.auth.dto.SignupResponseDto;
import jakarta.validation.Valid;

public interface AuthService {
    SignupResponseDto signup(SignupRequestDto requestDto);

    SignupResponseDto adminSignup(AdminSignupRequestDto requestDto);

}

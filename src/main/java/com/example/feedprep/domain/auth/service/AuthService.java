package com.example.feedprep.domain.auth.service;

import com.example.feedprep.domain.auth.dto.SignupRequestDto;
import com.example.feedprep.domain.auth.dto.SignupResponseDto;

public interface AuthService {
    SignupResponseDto signup(SignupRequestDto requestDto);

}

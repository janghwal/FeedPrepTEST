package com.example.feedprep.domain.auth.controller;

import com.example.feedprep.common.exception.enums.SuccessCode;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.auth.dto.*;
import com.example.feedprep.domain.auth.service.AuthService;

import com.example.feedprep.domain.user.enums.UserRole;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminAuthController {
    private final AuthService authService;

    // 관리자 회원 가입
    @PostMapping("/signup")
    ResponseEntity<ApiResponseDto<SignupResponseDto>> signup(@RequestBody @Valid AdminSignupRequestDto requestDto) {
        SignupResponseDto responseDto = authService.adminSignup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.success(SuccessCode.SIGNUP_SUCCESS, responseDto));
    }

    // 관리자 로그인
    @PostMapping("/login")
    ResponseEntity<ApiResponseDto<TokenResponseDto>> login(@RequestBody @Valid LoginRequestDto requestDto) {
        Set<String> allowedRoles = Set.of("ADMIN");
        TokenResponseDto responseDto =  authService.login(requestDto, allowedRoles);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.success(SuccessCode.LOGIN_SUCCESS, responseDto));
    }
}

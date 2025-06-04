package com.example.feedprep.domain.auth.controller;

import com.example.feedprep.common.exception.enums.SuccessCode;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.auth.dto.AdminSignupRequestDto;
import com.example.feedprep.domain.auth.dto.SignupRequestDto;
import com.example.feedprep.domain.auth.dto.SignupResponseDto;
import com.example.feedprep.domain.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminAuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<ApiResponseDto<SignupResponseDto>> signup(@RequestBody @Valid AdminSignupRequestDto requestDto) {
        SignupResponseDto responseDto = authService.adminSignup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.success(SuccessCode.SIGNUP_SUCCESS, responseDto));
    }
}

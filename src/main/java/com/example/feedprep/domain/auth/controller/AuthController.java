package com.example.feedprep.domain.auth.controller;

import com.example.feedprep.common.exception.enums.SuccessCode;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.auth.dto.SignupRequestDto;
import com.example.feedprep.domain.auth.dto.SignupResponseDto;
import com.example.feedprep.domain.auth.service.AuthService;
import com.example.feedprep.domain.user.dto.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    // 일반 회원 가입
    @PostMapping("/signup")
    ResponseEntity<ApiResponseDto<SignupResponseDto>> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        SignupResponseDto responseDto =  authService.signup(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.success(SuccessCode.SIGNUP_SUCCESS, responseDto));
    }



}

package com.example.feedprep.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    // 회원가입 및 로그인 성공
    SIGNUP_SUCCESS(HttpStatus.CREATED,"회원가입에 성공하였습니다." ),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공하였습니다."),

    ;


    private final HttpStatus httpStatus;
    private final String message;
}

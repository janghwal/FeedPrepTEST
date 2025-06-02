package com.example.feedprep.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ErrorCode {

    //인증
    MISSING_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다."),
    INVALID_JWT_SIGNATURE(HttpStatus.UNAUTHORIZED, "유효하지 않은 JWT 서명입니다."),
    NOT_MATCH_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다."),

    //유저
    INVALID_USER_ROLE(HttpStatus.BAD_REQUEST, "잘못된 역할입니다. STUDENT, PENDING_TUTOR, ADMIN 중 하나를 선택해 주세요"),
    NOT_FOUND_TUTOR(HttpStatus.BAD_REQUEST,"검색 된 튜터가 없습니다."),




    ;

    private final HttpStatus httpStatus;
    private final String message;
}

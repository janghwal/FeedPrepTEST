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

    // 구독
    CANNOT_SUBSCRIBE_SELF(HttpStatus.BAD_REQUEST, "자기 자신을 구독할 수 없습니다."),
    SUBSCRIPTION_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 구독 정보입니다."),
    UNAUTHORIZED_SUBSCRIPTION_ACCESS(HttpStatus.BAD_REQUEST, "해당 구독 정보를 수정할 수 있는 권한이 없습니다."),
    INVALID_ENUM_GETTYPE(HttpStatus.BAD_REQUEST, "유효하지 않은 GetType 입니다."),
    // 유저 모듈 임시 에러 코드
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String message;
}

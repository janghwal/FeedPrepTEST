package com.example.feedprep.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 잘못된 요청
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다." ),

    // Forbidden
    INVALID_ROLE_REQUEST(HttpStatus.FORBIDDEN, "해당 역할로 가입할 수 없습니다."),
    UNAUTHORIZED_ROLE_LOGIN(HttpStatus.FORBIDDEN, "요청하신 로그인 경로에서는 해당 역할의 계정으로 로그인할 수 없습니다."),
    FORBIDDEN_LOGOUT_ATTEMPT(HttpStatus.FORBIDDEN, "다른 사용자의 로그아웃 요청은 허용되지 않습니다."),

    //인증
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    MISSING_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    INVALID_JWT_SIGNATURE(HttpStatus.UNAUTHORIZED, "유효하지 않은 JWT 서명입니다."),
    INVALID_SECRET_CODE(HttpStatus.UNAUTHORIZED, "유효하지 않은 시크릿 코드입니다." ),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED,"이미 만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED,"지원하지 않는 토큰입니다."),


    // 구독
    CANNOT_SUBSCRIBE_SELF(HttpStatus.BAD_REQUEST, "자기 자신을 구독할 수 없습니다."),
    SUBSCRIPTION_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 구독 정보입니다."),
    UNAUTHORIZED_SUBSCRIPTION_ACCESS(HttpStatus.BAD_REQUEST, "해당 구독 정보를 수정할 수 있는 권한이 없습니다."),
    INVALID_ENUM_GETTYPE(HttpStatus.BAD_REQUEST, "유효하지 않은 GetType 입니다."),

    // 유저 모듈 임시 에러 코드
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),

    // 피드백 요청 생성
    // 사용자 인증 관련
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다. " ),
    UNAUTHORIZED_REQUESTER_ACCESS(HttpStatus.FORBIDDEN, "해당 요청을 수행할 권한이 없습니다."),
    TUTOR_NOT_FOUND(HttpStatus.NOT_FOUND, "튜터 정보를 찾을 수 없습니다."),
    INVALID_DOCUMENT(HttpStatus.NOT_FOUND, "존재하지 않는 문서입니다."),
    SELF_FEEDBACK_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "자신에게는 피드백을 요청할 수 없습니다."),
    CANNOT_EDIT_COMPLETED_REQUEST(HttpStatus.BAD_REQUEST, "이미 완료된 피드백은 수정할 수 없습니다."),
    // 요청 취소 관련
    CANNOT_CANCEL_COMPLETED(HttpStatus.BAD_REQUEST, "이미 완료된 피드백은 취소할 수 없습니다."),
    UNAUTHORIZED_CANCEL(HttpStatus.FORBIDDEN, "이 피드백 요청을 취소할 권한이 없습니다."),

    // 조회/접근 관련
    FEEDBACK_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 피드백 요청을 찾을 수 없습니다."),
    UNAUTHORIZED_ACCESS(HttpStatus.FORBIDDEN, "이 피드백에 접근할 수 없습니다."),

    // 일반 오류
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "피드백 처리 중 내부 오류가 발생했습니다."),


   ;


    private final HttpStatus httpStatus;
    private final String message;
}

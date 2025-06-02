package com.example.feedprep.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    // 회원가입 및 로그인 성공
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공하였습니다."),

    // 구독 모듈
    SUBSCRIBED(HttpStatus.CREATED, "구독하였습니다."),
    UNSUBSCRIBED(HttpStatus.OK, "구독을 취소하였습니다."),
    SUBSCRIPTION_LIST(HttpStatus.OK, "구독 목록입니다."),
    SUBSCRIBER_LIST(HttpStatus.OK, "구독자 목록입니다."),

   //피드백 요청 취소 성공
    OK_FEEDBACK_REQUEST_CANCELED(HttpStatus.OK, "정상적으로 취소 되었습니다.")

    ;

    private final HttpStatus httpStatus;
    private final String message;
}

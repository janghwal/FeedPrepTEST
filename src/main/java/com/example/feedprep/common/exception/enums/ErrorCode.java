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


    // 피드백 요청 에러코드
    // 사용자 인증 관련
    TUTOR_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 튜터를 찾을 수 없습니다."),
    INVALID_DOCUMENT(HttpStatus.NOT_FOUND, "존재하지 않는 문서입니다."),
    FEEDBACK_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 피드백 요청을 찾을 수 없습니다."),

    UNAUTHORIZED_ACCESS(HttpStatus.FORBIDDEN, "이 피드백에 접근할 수 없습니다."),
    UNAUTHORIZED_REQUESTER_ACCESS(HttpStatus.FORBIDDEN, "해당 요청을 수행할 권한이 없습니다."),
    UNAUTHORIZED_CANCEL(HttpStatus.FORBIDDEN, "이 피드백 요청을 취소할 권한이 없습니다."),

    SELF_FEEDBACK_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "자신에게는 피드백을 요청할 수 없습니다."),
    CANNOT_EDIT_COMPLETED_REQUEST(HttpStatus.BAD_REQUEST, "이미 완료된 피드백은 수정할 수 없습니다."),
    CANNOT_CANCEL_COMPLETED(HttpStatus.BAD_REQUEST, "이미 완료된 피드백은 취소할 수 없습니다."),

    // 일반 오류
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "피드백 처리 중 내부 오류가 발생했습니다."),

    //피드백 작성  에러코드
    INVALID_REQUEST_STATE(HttpStatus.BAD_REQUEST, "요청이 취소되었거나 완료된 상태입니다."),
    CANNOT_EDIT_PENDING_FEEDBACK(HttpStatus.CONFLICT, "작성 대기중인 피드백은 수정이 불가합니다."),
    CANNOT_REJECT_NON_PENDING_FEEDBACK(HttpStatus.CONFLICT, "작성 대기중인 피드백만 거절할 수 있습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}

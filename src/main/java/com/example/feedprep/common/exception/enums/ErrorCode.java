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



    // 피드백 요청 생성
    // 사용자 인증 관련
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
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "피드백 처리 중 내부 오류가 발생했습니다.")

    ;

    private final HttpStatus httpStatus;
    private final String message;
}

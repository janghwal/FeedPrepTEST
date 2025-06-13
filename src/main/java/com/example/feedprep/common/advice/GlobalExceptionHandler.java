package com.example.feedprep.common.advice;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.common.response.ApiResponseDto;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 커스텀 예외(CustomException)를 처리하는 핸들러
     * - 개발자가 정의한 도메인/비즈니스 예외
     * - 예: 로그인 실패 등
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponseDto<?>> handleCustomException(CustomException e, HttpServletRequest httpServletRequest) {

        return ResponseEntity.status(e.getHttpStatus())
                .body(ApiResponseDto.fail(e.getErrorCode(), httpServletRequest.getRequestURI()));

    }

    // 정의된 ENUM과 타입 불일치 오류
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponseDto<?>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest httpServletRequest) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDto.fail(ErrorCode.INVALID_ENUM_TYPE, httpServletRequest.getRequestURI()));

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponseDto<?>> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest httpServletRequest) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDto.fail(ErrorCode.DUPLICATE_RESOURCE, httpServletRequest.getRequestURI()));
    }
}

package com.example.feedprep.common.exception.base;

import com.example.feedprep.common.exception.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class CustomException extends AuthenticationException {

  private final ErrorCode errorCode;

  public CustomException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public HttpStatus getHttpStatus() {
    return errorCode.getHttpStatus();
  }
}

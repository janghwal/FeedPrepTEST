package com.example.feedprep.domain.user.enums;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import java.util.Arrays;

public enum UserRole {
    STUDENT, APPROVED_TUTOR, PENDING_TUTOR, ADMIN;

    public static UserRole of(String role) {
        return Arrays.stream(UserRole.values())
            .filter(r -> r.name().equalsIgnoreCase(role))
            .findFirst()
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_USER_ROLE));
    }
}

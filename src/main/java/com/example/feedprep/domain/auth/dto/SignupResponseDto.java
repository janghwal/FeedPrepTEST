package com.example.feedprep.domain.auth.dto;

import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupResponseDto {

    private final Long userId;
    private final String name;
    private final String email;
    private final UserRole role;

    public static SignupResponseDto from(User user) {
        return new SignupResponseDto(user.getUserId(), user.getName(), user.getEmail(), user.getRole());
    }

}

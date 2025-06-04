package com.example.feedprep.domain.auth.dto;

import com.example.feedprep.domain.user.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "이름은 필수입니다.")
    private final String name;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "비밀번호는 소문자, 대문자, 숫자를 포함한 8자 이상이어야 합니다.")
    private final String password;

    @NotNull(message = "역할은 필수입니다.")
    private final String role;
}

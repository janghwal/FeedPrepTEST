package com.example.feedprep.domain.auth.dto;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequestDto {

    private final String email;
    private final String password;
}

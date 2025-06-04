package com.example.feedprep.domain.user.dto.response;

import com.example.feedprep.domain.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class TutorResponseDto {

    private Long userId;
    private String name;
    private String email;
    private UserRole role;
}

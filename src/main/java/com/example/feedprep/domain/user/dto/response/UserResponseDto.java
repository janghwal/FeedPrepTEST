package com.example.feedprep.domain.user.dto.response;

import com.example.feedprep.domain.user.enums.UserRole;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String name;
    private String email;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}

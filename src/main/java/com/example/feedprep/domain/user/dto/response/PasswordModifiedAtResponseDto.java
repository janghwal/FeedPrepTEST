package com.example.feedprep.domain.user.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordModifiedAtResponseDto {

    private LocalDateTime modifiedAt;
}

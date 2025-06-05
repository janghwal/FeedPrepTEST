package com.example.feedprep.domain.user.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class PasswordModifiedAtResponseDto {

    private LocalDateTime modifiedAt;
}

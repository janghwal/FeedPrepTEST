package com.example.feedprep.domain.document.dto.responseDto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DocumentListResponseDto {

    private Long documentId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}

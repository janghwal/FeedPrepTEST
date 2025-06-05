package com.example.feedprep.domain.document.dto.responseDto;


import com.example.feedprep.domain.document.entity.Document;
import com.example.feedprep.domain.user.enums.UserRole;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DocumentResponseDto {

    private Long documentId;
    private String name;
    private UserRole Role;
    private String fileUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public DocumentResponseDto(Document document) {
        this.documentId = document.getDocumentId();
        this.name = document.getUser().getName();
        this.Role = document.getUser().getRole();
        this.fileUrl = document.getFileUrl();
        this.createdAt = document.getCreatedAt();
        this.modifiedAt = document.getModifiedAt();
    }
}

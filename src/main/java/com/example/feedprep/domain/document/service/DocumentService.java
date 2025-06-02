package com.example.feedprep.domain.document.service;

import com.example.feedprep.domain.document.dto.responseDto.DocumentResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

    DocumentResponseDto createDocument(MultipartFile file, String resume, Long tokenMyId);
}

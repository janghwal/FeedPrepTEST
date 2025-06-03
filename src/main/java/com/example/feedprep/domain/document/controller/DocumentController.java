package com.example.feedprep.domain.document.controller;


import static com.example.feedprep.common.exception.enums.SuccessCode.*;

import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.document.dto.responseDto.DocumentListResponseDto;
import com.example.feedprep.domain.document.dto.responseDto.DocumentResponseDto;
import com.example.feedprep.domain.document.entity.Document;
import com.example.feedprep.domain.document.service.DocumentService;
import java.io.IOException;
import java.security.Provider.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final TokenInfo tokenInfo;

    @PostMapping
    public ResponseEntity<ApiResponseDto<DocumentResponseDto>> createDocument(
        @RequestParam("file")MultipartFile file
    ) throws IOException {

        Long tokenMyId = tokenInfo.getTokenInfo(authheader);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDto.success(CREATE_DOCUMENT_SUCCESS,
                documentService.createDocument(file,"resume",tokenMyId)));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<DocumentListResponseDto>>> getMyDocumentList() {
        Long tokenMyId = tokenInfo.getTokenInfo(authheader);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDto.success(GET_MYDOCUMENLIST_SUCCESS,
                documentService.getMyDocumentList(tokenMyId)));
    }
}

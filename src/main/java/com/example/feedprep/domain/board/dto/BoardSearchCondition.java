package com.example.feedprep.domain.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchCondition {
    private String tag;      // "질문", "면접 후기", "정보" 등
    private String keyword;  // 제목/내용 검색어
    private String sortBy;   // "latest", "popular" 등
}
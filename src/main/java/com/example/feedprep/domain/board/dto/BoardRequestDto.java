package com.example.feedprep.domain.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequestDto {
    private String title;
    private String content;
    private String tag;       // 질문, 후기, 정보 등
    private boolean secret;   // 비밀글 여부
    private Long userId;      // 작성자 ID (임시)
}
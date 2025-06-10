package com.example.feedprep.domain.board.dto;

import com.example.feedprep.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String tag;
    private boolean secret;
    private Long userId;
    private int viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardResponseDto from(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .tag(board.getTag())
                .secret(board.isSecret())
                .userId(board.getUser().getUserId())
                .viewCount(board.getViewCount())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}
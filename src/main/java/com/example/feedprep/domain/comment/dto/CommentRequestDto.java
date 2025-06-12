package com.example.feedprep.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private Long boardId;    // 댓글을 작성할 게시글 ID
    private String content;  // 댓글 내용
}
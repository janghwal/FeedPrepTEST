package com.example.feedprep.domain.comment.dto;

import com.example.feedprep.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CommentResponseDto {
    private Long id;
    private String content;
    private Long userId;
    private String username;
    private boolean isOwner;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> replies;

    public static CommentResponseDto from(Comment comment, Long currentUserId) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.isDeleted() ? "[삭제된 댓글입니다.]" : comment.getContent())
                .userId(comment.getUser().getUserId())
                .username(comment.getUser().getName())
                .isOwner(comment.getUser().getUserId().equals(currentUserId))
                .isDeleted(comment.isDeleted())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static CommentResponseDto fromWithReplies(Comment comment, Long currentUserId) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.isDeleted() ? "[삭제된 댓글입니다.]" : comment.getContent())
                .userId(comment.getUser().getUserId())
                .username(comment.getUser().getName())
                .isOwner(comment.getUser().getUserId().equals(currentUserId))
                .isDeleted(comment.isDeleted())
                .createdAt(comment.getCreatedAt())
                .replies(comment.getChildren().stream()
                        .map(reply -> from(reply, currentUserId))
                        .collect(Collectors.toList()))
                .build();
    }
}
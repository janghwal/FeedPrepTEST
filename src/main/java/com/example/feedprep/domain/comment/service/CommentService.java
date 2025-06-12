package com.example.feedprep.domain.comment.service;

import com.example.feedprep.domain.comment.dto.CommentRequestDto;
import com.example.feedprep.domain.comment.dto.CommentReplyRequestDto;
import com.example.feedprep.domain.comment.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {

    void createComment(Long boardId, Long userId, CommentRequestDto requestDto);

    void replyComment(Long boardId, Long parentCommentId, Long userId, CommentReplyRequestDto requestDto);

    List<CommentResponseDto> getComments(Long boardId, Long userId);

    void updateComment(Long commentId, Long userId, CommentRequestDto requestDto);

    void deleteComment(Long commentId, Long userId);
}
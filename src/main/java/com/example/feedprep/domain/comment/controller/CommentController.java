package com.example.feedprep.domain.comment.controller;

import com.example.feedprep.domain.comment.dto.CommentRequestDto;
import com.example.feedprep.domain.comment.dto.CommentReplyRequestDto;
import com.example.feedprep.domain.comment.dto.CommentResponseDto;
import com.example.feedprep.domain.comment.service.CommentService;
import com.example.feedprep.common.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards/{boardId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<Void> createComment(@PathVariable Long boardId,
                                              @RequestBody CommentRequestDto requestDto) {
        Long userId = SecurityUtil.getCurrentUserId();
        commentService.createComment(boardId, userId, requestDto);
        return ResponseEntity.ok().build();
    }

    // 대댓글 작성
    @PostMapping("/{commentId}/reply")
    public ResponseEntity<Void> replyComment(@PathVariable Long boardId,
                                             @PathVariable Long commentId,
                                             @RequestBody CommentReplyRequestDto requestDto) {
        Long userId = SecurityUtil.getCurrentUserId();
        commentService.replyComment(boardId, commentId, userId, requestDto);
        return ResponseEntity.ok().build();
    }

    // 댓글 전체 조회
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long boardId) {
        Long userId = SecurityUtil.getCurrentUserId();
        List<CommentResponseDto> comments = commentService.getComments(boardId, userId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId,
                                              @RequestBody CommentRequestDto requestDto) {
        Long userId = SecurityUtil.getCurrentUserId();
        commentService.updateComment(commentId, userId, requestDto);
        return ResponseEntity.ok().build();
    }

    // 댓글 삭제 (soft delete)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        Long userId = SecurityUtil.getCurrentUserId();
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.ok().build();
    }
}
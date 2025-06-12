package com.example.feedprep.domain.commentlike.controller;

import com.example.feedprep.domain.commentlike.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments/{commentId}/like")
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping
    public ResponseEntity<String> like(@PathVariable Long commentId) {
        boolean liked = commentLikeService.likeComment(commentId);
        return ResponseEntity.ok(liked ? "추천 완료" : "이미 추천한 댓글입니다.");
    }

    @DeleteMapping
    public ResponseEntity<String> cancel(@PathVariable Long commentId) {
        boolean result = commentLikeService.cancelLike(commentId);
        return ResponseEntity.ok(result ? "추천 취소 완료" : "추천하지 않은 댓글입니다.");
    }
}
package com.example.feedprep.domain.commentlike.service;

import com.example.feedprep.domain.comment.entity.Comment;
import com.example.feedprep.domain.comment.repository.CommentRepository;
import com.example.feedprep.domain.commentlike.entity.CommentLike;
import com.example.feedprep.domain.commentlike.repository.CommentLikeRepository;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.repository.UserRepository;
import com.example.feedprep.common.security.util.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public boolean likeComment(Long commentId) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        if (commentLikeRepository.existsByUserAndComment(user, comment)) {
            return false; // 이미 추천함
        }

        commentLikeRepository.save(CommentLike.of(user, comment));
        comment.increaseLikeCount();
        return true;
    }

    @Transactional
    public boolean cancelLike(Long commentId) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        CommentLike like = commentLikeRepository.findByUserAndComment(user, comment)
                .orElse(null);

        if (like == null) return false;

        commentLikeRepository.delete(like);
        comment.decreaseLikeCount();
        return true;
    }
}
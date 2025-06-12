package com.example.feedprep.domain.commentlike.repository;

import com.example.feedprep.domain.comment.entity.Comment;
import com.example.feedprep.domain.commentlike.entity.CommentLike;
import com.example.feedprep.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    boolean existsByUserAndComment(User user, Comment comment);
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
package com.example.feedprep.domain.comment.repository;

import com.example.feedprep.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardIdAndParentIsNull(Long boardId);

}
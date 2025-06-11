package com.example.feedprep.domain.commentlike.entity;

import com.example.feedprep.domain.comment.entity.Comment;
import com.example.feedprep.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    public static CommentLike of(User user, Comment comment) {
        return CommentLike.builder()
                .user(user)
                .comment(comment)
                .build();
    }
}
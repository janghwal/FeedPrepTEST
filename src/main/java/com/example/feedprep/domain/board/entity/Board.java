package com.example.feedprep.domain.board.entity;

import com.example.feedprep.domain.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String tag; // 예: "질문", "후기", "정보"

    @Column(nullable = false)
    private boolean secret; // 비밀글 여부 (튜터만 조회 가능)

    @Column(nullable = false)
    private Long userId; // 작성자 ID (User 테이블 FK로 연결 예정)

    private int viewCount = 0;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Board of(BoardRequestDto dto) {
        return Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .tag(dto.getTag())
                .secret(dto.isSecret())
                .userId(dto.getUserId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public void update(BoardRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.tag = dto.getTag();
        this.secret = dto.isSecret();
        this.updatedAt = LocalDateTime.now();
    }

    public void increaseViewCount() {
        this.viewCount++;
    }
}
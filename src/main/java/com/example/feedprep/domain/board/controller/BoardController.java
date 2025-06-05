package com.example.feedprep.domain.board.controller;

import com.example.feedprep.domain.board.dto.BoardRequestDto;
import com.example.feedprep.domain.board.dto.BoardResponseDto;
import com.example.feedprep.domain.board.dto.BoardSearchCondition;
import com.example.feedprep.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto requestDto) {
        BoardResponseDto response = boardService.createBoard(requestDto);
        return ResponseEntity.status(201).body(response);
    }

    // 게시글 목록 + 검색
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getBoards(BoardSearchCondition condition) {
        List<BoardResponseDto> response = boardService.getBoards(condition);
        return ResponseEntity.ok(response);
    }

    // 게시글 단건 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long boardId) {
        BoardResponseDto response = boardService.getBoard(boardId);
        return ResponseEntity.ok(response);
    }

    // 게시글 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<String> updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto requestDto) {
        boardService.updateBoard(boardId, requestDto);
        return ResponseEntity.ok("게시글이 수정되었습니다.");
    }

    // 게시글 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // 게시글 추천
    @PostMapping("/{boardId}/recommend")
    public ResponseEntity<String> recommendBoard(@PathVariable Long boardId) {
        int totalRecommendations = boardService.recommendBoard(boardId);
        return ResponseEntity.ok("추천 완료. 총 추천 수: " + totalRecommendations);
    }

    // 게시글 스크랩
    @PostMapping("/{boardId}/scrap")
    public ResponseEntity<String> scrapBoard(@PathVariable Long boardId) {
        boolean isScrapped = boardService.scrapBoard(boardId);
        return ResponseEntity.ok(isScrapped ? "스크랩 완료" : "이미 스크랩됨");
    }
}
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

    // 게시글 추천
    @PostMapping("/{boardId}/recommend")
    public ResponseEntity<Integer> recommendBoard(@PathVariable Long boardId) {
        int recommendCount = boardService.recommendBoard(boardId);
        return ResponseEntity.ok(recommendCount);
    }

    // 게시글 스크랩
    @PostMapping("/{boardId}/scrap")
    public ResponseEntity<String> scrapBoard(@PathVariable Long boardId) {
        boolean isScrapped = boardService.scrapBoard(boardId);
        if (isScrapped) {
            return ResponseEntity.ok("스크랩 완료");
        } else {
            return ResponseEntity.ok("이미 스크랩된 게시글입니다.");
        }
    }

    // 게시글 스크랩 취소
    @DeleteMapping("/{boardId}/scrap")
    public ResponseEntity<String> unscrapBoard(@PathVariable Long boardId) {
        boolean isRemoved = boardService.unscrapBoard(boardId);
        if (isRemoved) {
            return ResponseEntity.ok("스크랩 취소");
        } else {
            return ResponseEntity.ok("스크랩되지 않은 게시글입니다.");
        }
    }

    // 로그인 유저의 스크랩 목록 조회
    @GetMapping("/scraps")
    public ResponseEntity<List<BoardResponseDto>> getMyScrapList() {
        List<BoardResponseDto> scraps = boardService.getMyScrapList();
        return ResponseEntity.ok(scraps);
    }

    // 특정 게시글 스크랩 여부 확인
    @GetMapping("/{boardId}/scrap")
    public ResponseEntity<Boolean> isBoardScrapped(@PathVariable Long boardId) {
        boolean isScrapped = boardService.isBoardScrapped(boardId);
        return ResponseEntity.ok(isScrapped);
    }

    // (기존) 게시글 등록
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto requestDto) {
        BoardResponseDto response = boardService.createBoard(requestDto);
        return ResponseEntity.ok(response);
    }

    // (기존) 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getBoards(BoardSearchCondition condition) {
        List<BoardResponseDto> boards = boardService.getBoards(condition);
        return ResponseEntity.ok(boards);
    }

    // (기존) 게시글 단건 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long boardId) {
        BoardResponseDto board = boardService.getBoard(boardId);
        return ResponseEntity.ok(board);
    }

    // (기존) 게시글 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<Void> updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto requestDto) {
        boardService.updateBoard(boardId, requestDto);
        return ResponseEntity.ok().build();
    }

    // (기존) 게시글 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok().build();
    }
}
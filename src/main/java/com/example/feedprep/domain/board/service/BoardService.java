package com.example.feedprep.domain.board.service;

import com.example.feedprep.domain.board.dto.BoardRequestDto;
import com.example.feedprep.domain.board.dto.BoardResponseDto;
import com.example.feedprep.domain.board.dto.BoardSearchCondition;

import java.util.List;

public interface BoardService {

    boolean unscrapBoard(Long boardId);

    List<BoardResponseDto> getMyScrapList();

    boolean isBoardScrapped(Long boardId);

    BoardResponseDto createBoard(BoardRequestDto requestDto);

    List<BoardResponseDto> getBoards(BoardSearchCondition condition);

    BoardResponseDto getBoard(Long boardId);

    void updateBoard(Long boardId, BoardRequestDto requestDto);

    void deleteBoard(Long boardId);

    int recommendBoard(Long boardId);

    boolean scrapBoard(Long boardId);
}
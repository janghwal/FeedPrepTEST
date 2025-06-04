package com.example.feedprep.domain.board.service;

import com.example.feedprep.domain.board.dto.BoardRequestDto;
import com.example.feedprep.domain.board.dto.BoardResponseDto;
import com.example.feedprep.domain.board.dto.BoardSearchCondition;
import com.example.feedprep.domain.board.entity.Board;
import com.example.feedprep.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = Board.of(requestDto);
        boardRepository.save(board);
        return BoardResponseDto.from(board);
    }

    @Override
    public List<BoardResponseDto> getBoards(BoardSearchCondition condition) {
        // 기본 목록 조회만 일단 처리
        return boardRepository.findAll().stream()
                .map(BoardResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public BoardResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return BoardResponseDto.from(board);
    }

    @Override
    public void updateBoard(Long boardId, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        board.update(requestDto);
    }

    @Override
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Override
    public int recommendBoard(Long boardId) {
        // 추천 수 증가 로직은 이후 구현
        return 0;
    }

    @Override
    public boolean scrapBoard(Long boardId) {
        // 스크랩 처리 로직은 이후 구현
        return true;
    }
}
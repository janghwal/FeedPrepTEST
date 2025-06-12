package com.example.feedprep.domain.board.repository;

import com.example.feedprep.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByRecommendCountGreaterThanEqualOrderByRecommendCountDesc(int threshold);
}
package com.example.feedprep.domain.recommend.repository;

import com.example.feedprep.domain.board.entity.Board;
import com.example.feedprep.domain.recommend.entity.Recommend;
import com.example.feedprep.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    boolean existsByUserAndBoard(User user, Board board);
    Optional<Recommend> findByUserAndBoard(User user, Board board);
}
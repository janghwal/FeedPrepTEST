package com.example.feedprep.domain.scrap.repository;

import com.example.feedprep.domain.scrap.entity.Scrap;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    boolean existsByUserAndBoard(User user, Board board);

    Optional<Scrap> findByUserAndBoard(User user, Board board);

    List<Scrap> findAllByUser(User user);
}
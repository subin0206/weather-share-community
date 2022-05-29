package com.springproject.weathersharecommunity.repository;


import com.springproject.weathersharecommunity.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Board b SET b.score = :score")
    void updateScore(int score);

    List<Board> findAllByOrderByScoreDesc();


}

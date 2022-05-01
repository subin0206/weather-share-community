package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepositoryTest extends JpaRepository<Board, Long> {
//    Optional<Board> findByBoardId(Long id);
//    @Query(value = "Select b.user_id from Board b Where b.board_id = :board_id  ")
    public Long findUserIdById(Long id);
}

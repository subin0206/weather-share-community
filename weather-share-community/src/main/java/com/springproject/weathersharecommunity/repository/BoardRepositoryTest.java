package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepositoryTest extends JpaRepository<Board, Long> {
    BoardInfoMapping findByBoardId(Long id);
}

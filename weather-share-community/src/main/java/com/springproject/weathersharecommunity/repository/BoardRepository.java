package com.springproject.weathersharecommunity.repository;


import com.springproject.weathersharecommunity.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}

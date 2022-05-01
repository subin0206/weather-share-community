package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "Select i.url From Image i Where i.board_id = :boardId", nativeQuery = true)
    List<String> findUrlByBoardId(@Param("boardId") Long boardId);
}

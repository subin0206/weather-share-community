package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query(value = "Select * From Reply r Where r.board_id = :boardId", nativeQuery = true)
    List<Reply> findAllByBoardId(@Param("boardId")Long boardId);
}

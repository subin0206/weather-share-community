package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "SELECT * FROM Board b WHERE b.content LIKE %:keyword%", nativeQuery = true)
    List<Board> findAllSearch(String keyword);

    List<Board> findByMemberIdOrderByIdDesc(long memberId);

    @Query(value = "SELECT * FROM Board as b WHERE b.user_id IN (:followList)", nativeQuery = true)
    List<Board> followingPosts(@Param("followList") List<Long> followList);
}

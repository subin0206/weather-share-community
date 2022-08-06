package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findFollowByFromMemberIdAndToMemberId(long from_member_id, long to_member_Id);

    List<Follow> findAllByFromMemberId(long fromMemberId);

    List<Follow> findAllByToMemberId(long toMemberId);

    @Query(value = "SELECT COUNT(*) FROM follow WHERE from_member_id = :memberId", nativeQuery = true)
    int findFollowingCountById(long memberId);

    @Modifying
    @Query(value = "INSERT INTO follow(from_member_id, to_member_id) VALUES(:fromMemberId, :toMemberId)", nativeQuery = true)
    void follow(long fromMemberId, long toMemberId);

    @Modifying
    @Query(value = "DELETE FROM follow WHERE from_member_id = :fromMemberId AND to_member_id = :toMemberId", nativeQuery = true)
    void unFollow(long fromMemberId, long toMemberId);



    List<Follow> findToMemberIdByFromMemberId(long fromMemberId);
}
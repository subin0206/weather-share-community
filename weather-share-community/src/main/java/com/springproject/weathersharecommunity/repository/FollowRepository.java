package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    // int countByFollowerIdAndFollowingUserId(int id, String userId);
    // 팔로우 되어있는지 count하는 메서드

    void deleteById(Long id);

    Follow findFollowByFromMemberIdAndToMemberId(long from_member_id, long to_member_Id);

    //@Query(value = "SELECT * FROM follow WHERE from_member_id = :fromMemberId", nativeQuery = true)
    List<Follow> findAllByFromMemberId(long fromMemberId);

    List<Follow> findAllByToMemberId(long toMemberId);

    @Query(value = "SELECT COUNT(*) FROM follow WHERE to_member_id = :profileId", nativeQuery = true)
    int findFollowerCountById(long profileId);

    @Query(value = "SELECT COUNT(*) FROM follow WHERE from_member_id = :profileId", nativeQuery = true)
    int findFollowingCountById(long profileId);

    @Modifying
    @Query(value = "INSERT INTO follow(from_member_id, to_member_id) VALUES(:fromMemberId, :toMemberId)", nativeQuery = true)
    void follow(long fromMemberId, long toMemberId);

    @Modifying
    @Query(value = "DELETE FROM follow WHERE from_member_id = :fromMemberId AND to_member_id = :toMemberId", nativeQuery = true)
    void unFollow(long fromMemberId, long toMemberId);

    @Query(value = "SELECT * FROM board WHERE member_id IN (SELECT to_member_id FROM FOLLOW WHERE from_member_id = :id) order by id desc" , nativeQuery = true)
    List<Board> followingPosts();




}
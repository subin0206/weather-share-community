package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.Reply;
import com.springproject.weathersharecommunity.domain.Reply2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Reply2Repository extends JpaRepository<Reply2, Long> {
    @Query(value = "Select * From Reply2 r Where r.reply_id = :replyId", nativeQuery = true)
    List<Reply2> findAllByReplyId(@Param("replyId")Long replyId);
}

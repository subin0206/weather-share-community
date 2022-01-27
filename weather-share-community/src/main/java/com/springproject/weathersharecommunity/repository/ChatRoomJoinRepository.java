package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.ChatRoom;
import com.springproject.weathersharecommunity.domain.ChatRoomJoin;
import com.springproject.weathersharecommunity.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoin, Long> {
    List<ChatRoomJoin> findByMember(Member member);
    List<ChatRoomJoin> findByChatRoom(ChatRoom chatRoom);
}

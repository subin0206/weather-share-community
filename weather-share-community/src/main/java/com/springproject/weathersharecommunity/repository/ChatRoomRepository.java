package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    public Optional<ChatRoom> findById(Long id);

}

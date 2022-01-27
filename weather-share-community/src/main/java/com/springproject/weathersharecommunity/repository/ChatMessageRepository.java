package com.springproject.weathersharecommunity.repository;

import com.springproject.weathersharecommunity.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}

package com.springproject.weathersharecommunity.Controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ChatRoomDto {
    private Long id;
    private String writer;
    private String lastMessage;
    private LocalDateTime time;

    public void makeChatRoom(String message, String anotherUser, LocalDateTime time) {
        this.lastMessage = message;
        this.writer = anotherUser;
        this.time = time;
    }
}

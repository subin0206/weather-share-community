package com.springproject.weathersharecommunity.Controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    private Long chatRoomId;
    private String receiver;
    private String sender;
    private String message;
}

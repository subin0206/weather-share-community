package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.WeatherStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardGetDto {

    private Board board;

    private long boardId;

    private Member member;

    private String content;

    private boolean privacy;

    private LocalDateTime createDate = LocalDateTime.now();

    private WeatherStatus status; //날씨 상태[더워요, 따뜻해요, 딱 좋아요, 서늘해요, 추워요]

    private String presentTemperature;

    private String highestTemperature;

    private String lowestTemperature;

    private String jsonImages;
}

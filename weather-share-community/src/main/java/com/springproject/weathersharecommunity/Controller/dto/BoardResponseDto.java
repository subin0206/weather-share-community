package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Image;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.WeatherStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;

    private String content;

    private boolean privacy;

    private LocalDateTime createDate = LocalDateTime.now();

    private WeatherStatus status; //날씨 상태[더워요, 따뜻해요, 딱 좋아요, 서늘해요, 추워요]

    private String presentTemperature;

    private String highestTemperature;

    private String lowestTemperature;

    private List<String> images;
    private MemberResponseDto memberResponseDto;
    public BoardResponseDto(Board entity, List<String> images, MemberResponseDto memberResponseDto) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.images = images;
        this.memberResponseDto = memberResponseDto;
        this.status = entity.getStatus();
        this.highestTemperature = entity.getHighestTemperature();
        this.lowestTemperature = entity.getLowestTemperature();
        this.presentTemperature = entity.getPresentTemperature();
    }


}

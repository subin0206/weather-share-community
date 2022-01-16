package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Image;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.WeatherStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class BoardRequestDto {

    private Board board;

    private long boardId;

    private Member member;

    @NotEmpty(message = "내용을 작성해주세요")
    private String content;

    private boolean privacy;

    private LocalDateTime createDate;

    private WeatherStatus status; //날씨 상태[더워요, 따뜻해요, 딱 좋아요, 서늘해요, 추워요]

    private List<Image> images = new ArrayList<>();
}

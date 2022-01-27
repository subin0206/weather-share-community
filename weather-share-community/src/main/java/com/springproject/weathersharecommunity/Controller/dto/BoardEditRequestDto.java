package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Image;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.WeatherStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class BoardEditRequestDto {

    private long boardId;

    private String content;

    private boolean privacy;

    public BoardEditRequestDto(long boardId, String content, boolean privacy){
        this.boardId = boardId;
        this.content = content;
        this.privacy = privacy;
    }

}

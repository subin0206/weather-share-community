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
public class BoardImgResponseDto {

    private Long id;



    private String imgUrl;


    public BoardImgResponseDto(Board entity) {
        this.id = entity.getId();
        try{
            this.imgUrl = entity.getImages().get(0).getUrl();
        }catch(IndexOutOfBoundsException e){
            imgUrl = "";
        }
    }


}

package com.springproject.weathersharecommunity.Controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
@Data
public class FollowDto {
    private long id;
    private String name;
    private String profileImgUrl;
    private int followState;
    private int loginUser;

}

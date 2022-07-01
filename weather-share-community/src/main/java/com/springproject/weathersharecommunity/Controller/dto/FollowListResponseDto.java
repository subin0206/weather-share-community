package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Follow;

import com.springproject.weathersharecommunity.domain.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class FollowListResponseDto {

    private Long fromMemberId;
    private Long toMemberId;



    public FollowListResponseDto (Follow entity) {
        this.fromMemberId = entity.getFromMember().getId();
        this.toMemberId = entity.getToMember().getId();


    }
}

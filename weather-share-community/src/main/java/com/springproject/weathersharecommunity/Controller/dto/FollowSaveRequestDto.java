package com.springproject.weathersharecommunity.Controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class FollowSaveRequestDto {
    private Long fromMemberId;
    private Long toMemberId;


}

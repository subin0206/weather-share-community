package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BlockDto {
//    private long blockId;
    private long blockedMemberId;
}
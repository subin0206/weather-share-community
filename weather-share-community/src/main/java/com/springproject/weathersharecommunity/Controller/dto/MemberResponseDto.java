package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long id;
    private String nickName;
    private String userEmail;
    private String profileUrl;

    @Builder
    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.nickName = entity.getNickName();
        this.userEmail = entity.getUserEmail();
        this.profileUrl = entity.getProfileUrl();
    }
}

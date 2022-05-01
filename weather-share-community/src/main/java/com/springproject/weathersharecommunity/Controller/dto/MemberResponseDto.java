package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long id;
    private String userName;
    private String userEmail;
    private String profileUrl;

    @Builder
    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.userName = entity.getUsername();
        this.userEmail = entity.getUserEmail();
        this.profileUrl = entity.getProfileUrl();
    }
}

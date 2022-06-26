package com.springproject.weathersharecommunity.Controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberLoginRequestDto {
    String userEmail;
    String pwd;

    @Builder
    public MemberLoginRequestDto(String userEmail, String pwd) {
        this.userEmail = userEmail;
        this.pwd = pwd;
    }

}

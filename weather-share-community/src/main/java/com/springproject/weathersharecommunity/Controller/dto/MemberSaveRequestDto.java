package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;

@Getter @Setter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private PasswordEncoder passwordEncoder;
    private String nickName;
    private String userEmail;
    private String pwd;
    private String profileUrl;
    private ArrayList<String> roles;
    @Builder
    public MemberSaveRequestDto(String nickName, String userEmail, String pwd) {
        this.nickName = nickName;
        this.userEmail = userEmail;
        this.pwd = pwd;
    }

    public Member toEntity(){
        return Member.builder()
                .nickName(nickName)
                .userEmail(userEmail)
                .pwd(pwd)
                .profileUrl(profileUrl)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }
}

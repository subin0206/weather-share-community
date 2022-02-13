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
    private String userName;
    private String userEmail;
    private String pwd;
    private String profileUrl;
    private ArrayList<String> roles;
    @Builder
    public MemberSaveRequestDto(String userName, String userEmail, String pwd) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.pwd = pwd;
    }

    public Member toEntity(){
        return Member.builder()
                .userName(userName)
                .userEmail(userEmail)
                .pwd(pwd)
                .profileUrl(profileUrl)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }
}

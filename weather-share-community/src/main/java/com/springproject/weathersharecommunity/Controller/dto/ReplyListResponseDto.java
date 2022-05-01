package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ReplyListResponseDto {

    private Long id;

    private String content;
    private String memberName;
    private String profileUrl;

    public ReplyListResponseDto(Reply entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.memberName = entity.getMember().getUsername();
        this.profileUrl = entity.getMember().getProfileUrl();
    }
}

package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Reply;
import com.springproject.weathersharecommunity.domain.Reply2;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Reply2ListResponseDto {

    private Long id;

    private String content;
    private String memberName;
    private String profileUrl;

    public Reply2ListResponseDto(Reply2 entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.memberName = entity.getMember().getUsername();
        this.profileUrl = entity.getMember().getProfileUrl();
    }

}

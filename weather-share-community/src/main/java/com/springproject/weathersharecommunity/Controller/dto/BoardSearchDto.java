package com.springproject.weathersharecommunity.Controller.dto;


import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardSearchDto {

    private long boardId;
    private Member member;
    private String content;

    public BoardSearchDto(Board entity) {
        this.boardId = entity.getId();
        this.member = entity.getMember();
        this.content= entity.getContent();
    }
}


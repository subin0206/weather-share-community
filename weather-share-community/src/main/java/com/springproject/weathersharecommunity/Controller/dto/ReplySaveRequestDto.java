package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplySaveRequestDto {

    private String content;
    private Member member;
    private Board board;

    @Builder
    public ReplySaveRequestDto(String content, Member member, Board board) {
        this.content = content;
        this.member = member;
        this.board = board;
    }

    public Reply toEntity() {
        return Reply.builder()
                .content(content)
                .board(board)
                .member(member)
                .build();
    }
}

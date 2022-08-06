package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.Reply;
import com.springproject.weathersharecommunity.domain.Reply2;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyAgainRequestDto {

    private Reply reply;
    private String content;
    private Member member;
    private Board board;

    @Builder
    public ReplyAgainRequestDto(String content, Member member, Board board, Reply reply) {
        this.content = content;
        this.member = member;
        this.board = board;
        this.reply = reply;
    }

    public Reply2 toEntity() {
        return Reply2.builder()
                .content(content)
                .board(board)
                .member(member)
                .reply(reply)
                .build();
    }
}

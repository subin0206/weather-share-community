package com.springproject.weathersharecommunity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private Board board;

    @JoinColumn(name = "user_id")
    @ManyToOne
    @JsonIgnore
    private Member member;

    @Builder
    public Likes(Board board, Member member) {
        this.board = board;
        this.member = member;
    }

    public void mappingMember(Member member) {
        this.member = member;
        member.mappingBoardLike(this);
    }

    public void mappingBoard(Board board) {
        this.board = board;
        board.mappingBoardLike(this);
    }
}

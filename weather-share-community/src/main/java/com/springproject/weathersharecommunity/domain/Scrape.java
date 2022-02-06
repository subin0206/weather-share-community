package com.springproject.weathersharecommunity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Scrape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private Board board;

    @JoinColumn(name = "user_id")
    @ManyToOne
    @JsonIgnore
    private Member member;

    @Builder
    public Scrape(Board board, Member member) {
        this.board = board;
        this.member = member;
    }
}

//package com.springproject.weathersharecommunity.domain;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Getter @Setter
//@NoArgsConstructor
//public class Score {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "score_id")
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "board_id")
//    private Board board;
//
//    private int score;
//
//    @Builder
//    public Score(Board board, int score) {
//        this.board = board;
//        this.score = score;
//    }
//
//    public void countScore(Board board) {
//         this.score = (int) board.getCreateDate().getDayOfMonth() - (int)board.getLikesCount();
//    }
//}

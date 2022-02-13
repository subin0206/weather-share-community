package com.springproject.weathersharecommunity.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter
public class Image {
    @Id @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String originFileName; // 파일 원본명

    private String url;

    private Long fileSize;

    @Builder
    public Image(String originFileName, String url, Long fileSize){
        this.originFileName = originFileName;
        this.url = url;
        this.fileSize = fileSize;
    }

    public void setBoard(Board board){
        this.board = board;
    }
}

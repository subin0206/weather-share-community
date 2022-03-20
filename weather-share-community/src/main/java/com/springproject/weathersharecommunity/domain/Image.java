package com.springproject.weathersharecommunity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity @Getter
public class Image {
    @Id @GeneratedValue()
    @Column(name = "image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnore
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

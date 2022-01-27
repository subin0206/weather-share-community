package com.springproject.weathersharecommunity.domain;

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

    private String url;
}

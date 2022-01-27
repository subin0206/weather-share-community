package com.springproject.weathersharecommunity.domain;

import com.springproject.weathersharecommunity.domain.clothes.Clothes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    private boolean privacy;

    @CreatedDate
    private LocalDateTime createDate;

    private WeatherStatus status; //날씨 상태[더워요, 따뜻해요, 딱 좋아요, 서늘해요, 추워요]

    @OneToMany(mappedBy = "board")
    private List<Image> images = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "board")
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

}

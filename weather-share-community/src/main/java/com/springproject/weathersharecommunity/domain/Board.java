package com.springproject.weathersharecommunity.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonBackReference
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

    @OneToMany(mappedBy = "board")
    @JsonIgnore
    private List<Likes> likesList;

    @OneToMany(mappedBy = "board")
    @JsonIgnore
    private List<Scrape> scrapeList;

    private long likesCount;

    @OneToMany(mappedBy = "board")
    private List<Reply> replies;

    public void mappingBoardLike(Likes likes) {
        this.likesList.add(likes);
    }

    public void updateLikeCount() {
        this.likesCount = likesList.size();
    }

    public void discountLike(Likes likes) {
        this.likesList.remove(likes);
    }
}

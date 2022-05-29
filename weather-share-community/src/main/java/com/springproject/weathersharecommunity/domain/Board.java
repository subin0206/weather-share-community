package com.springproject.weathersharecommunity.domain;

import com.fasterxml.jackson.annotation.*;
import com.springproject.weathersharecommunity.domain.clothes.Clothes;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
//    @JsonIgnore
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

    private long popularCount;

    @OneToMany(mappedBy = "board")
    @JsonBackReference
    private List<Reply> replies;

    private int score;

    public void mappingBoardLike(Likes likes) {
        this.likesList.add(likes);
    }

    public void updateLikeCount() {
        this.likesCount = likesList.size();
    }

    public void discountLike(Likes likes) {
        this.likesList.remove(likes);
    }

    private String presentTemperature;

    private String highestTemperature;

    private String lowestTemperature;

    public void countScore(Board board) {
        LocalDate now = LocalDate.now();
        Calendar getToday = Calendar.getInstance();
        getToday.setTime(new Date());
        Calendar boardDate = Calendar.getInstance();
        boardDate.setTime(java.sql.Timestamp.valueOf(board.getCreateDate()));
        long diffSec = (getToday.getTimeInMillis() - boardDate.getTimeInMillis()) / 1000;
        long diffDays = diffSec / (24 * 60 * 60);

        score = 0;

        if (diffDays == 0) {
            score += 100;
        } else if ( diffDays > 0 && diffDays <= 7) {
            score += 50;
        } else if (diffDays > 7 && diffDays <= 14) {
            score += 30;
        } else if (diffDays > 14 && diffDays<= 21) {
            score += 10;
        }
        else{
            score += 0;
        }
        this.score = (int) (score + board.getLikesCount() * 10);
    }

    @Builder
    public Board(String content, Member member, boolean privacy, LocalDateTime createDate, WeatherStatus status, Clothes clothes, String presentTemperature, String highestTemperature, String lowestTemperature, Long score) {
        this.content = content;
        this.member = member;
        this.privacy = privacy;
        this.createDate = createDate;
        this.status = status;
        this.clothes = clothes;
        this.presentTemperature = presentTemperature;
        this.highestTemperature = highestTemperature;
        this.lowestTemperature = lowestTemperature;
    }


}

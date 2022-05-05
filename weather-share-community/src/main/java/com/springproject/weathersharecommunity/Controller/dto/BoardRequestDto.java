package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Image;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.WeatherStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class BoardRequestDto {

    private Member member;

    private String content;

    private boolean privacy;

    private LocalDateTime createDate = LocalDateTime.now();

    private WeatherStatus status; //날씨 상태[더워요, 따뜻해요, 딱 좋아요, 서늘해요, 추워요]

    private String presentTemperature;

    private String highestTemperature;

    private String lowestTemperature;

    @Builder
    public BoardRequestDto(Member member ,String content, boolean privacy, LocalDateTime createDate, WeatherStatus status, String presentTemperature, String highestTemperature, String lowestTemperature) {
        this.member = member;
        this.content = content;
        this.privacy = privacy;
        this.createDate = createDate;
        this.status = status;
        this.presentTemperature = presentTemperature;
        this.highestTemperature = highestTemperature;
        this.lowestTemperature = lowestTemperature;
    }

    public Board toEntity() {
        return Board.builder()
                .content(content)
                .createDate(createDate)
                .member(member)
                .status(status)
                .highestTemperature(highestTemperature)
                .lowestTemperature(lowestTemperature)
                .presentTemperature(presentTemperature)
                .privacy(privacy)
                .build();
    }
}

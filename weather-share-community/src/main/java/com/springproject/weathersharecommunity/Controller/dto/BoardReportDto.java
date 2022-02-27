package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class BoardReportDto {

    private String type;

    private Member member; //신고당한 유저

    private Long typeId;

    private Long reportId;

    private LocalDateTime createTime;

}

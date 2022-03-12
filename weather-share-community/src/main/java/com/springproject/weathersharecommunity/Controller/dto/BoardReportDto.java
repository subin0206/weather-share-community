package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class BoardReportDto {

    private String type; //게시물 or 댓글

    private Long reportedUserId; //신고당한 유저

    private Long typeId; //신고당한 글 or 댓글 번호

//    private Long reportId;

    private LocalDateTime createTime;

}

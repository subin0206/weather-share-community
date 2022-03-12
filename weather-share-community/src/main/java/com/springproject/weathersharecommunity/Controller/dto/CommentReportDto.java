package com.springproject.weathersharecommunity.Controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentReportDto {
    private String type;

    private Long reportedUserId; //신고당한 유저

    private Long typeId; //댓글 번호
}

package com.springproject.weathersharecommunity.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Report {
    @Id
    @GeneratedValue
    @Column(name = "report_id")
    private Long id;

    @CreatedDate
    @Column(name="report_date_time")
    private LocalDateTime createDate;

    private String type; //[board, comment, dm]

    private Long typeId; //신고당한 글/댓글/DM 번호

    private Long reportUserId; //신고한 유저 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member; //신고 당한 유저

}

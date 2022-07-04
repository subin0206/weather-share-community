package com.springproject.weathersharecommunity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Reply2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply2_id")
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "reply_id")
    private Reply reply;

    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 120)
    private String content;

    @ManyToOne
    @JoinColumn(name = "boardId")
    @JsonManagedReference
    @JsonIgnore
    private Board board;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Member member;

    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public Reply2(@Size(min = 1, max = 255) String content, Reply reply,Board board, Member member) {
        this.content = content;
        this.reply = reply;
        this.board = board;
        this.member = member;
    }
}

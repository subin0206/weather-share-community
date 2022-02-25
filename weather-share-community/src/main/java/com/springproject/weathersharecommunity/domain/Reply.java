package com.springproject.weathersharecommunity.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Reply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 120)
    private String content;

    @ManyToOne
    @JoinColumn(name = "boardId")
    @JsonManagedReference
    private Board board;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Member member;

    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public Reply(@Size(min = 1, max = 255) String content, Board board, Member member) {
        this.content = content;
        this.board = board;
        this.member = member;
    }
}

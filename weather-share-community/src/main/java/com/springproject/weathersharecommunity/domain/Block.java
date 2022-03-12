package com.springproject.weathersharecommunity.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Block {
    @Id
    @GeneratedValue
    @Column(name = "block_id")
    private Long id;

    private Member member;

    private Member blockedMember;
    //private List<Member> blockedMember = new ArrayList<>(); //차단당한 멤버

}

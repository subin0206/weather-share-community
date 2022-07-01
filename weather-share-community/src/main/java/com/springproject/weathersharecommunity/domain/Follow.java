package com.springproject.weathersharecommunity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter @NoArgsConstructor
@Entity
public class Follow {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "from_member_id")
    @ManyToOne
    private Member fromMember; //구독 하는 사람

    @JoinColumn(name = "to_member_id")
    @ManyToOne
    private Member toMember; //구독 당하는 사람



    @Builder
    public Follow (Member fromMember, Member toMember) {
        this.fromMember = fromMember;
        this.toMember = toMember;


    }
}

package com.springproject.weathersharecommunity.domain.clothes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Bottom {
    @Id
    @GeneratedValue
    @Column(name = "bottom_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    private boolean jeans; //청바지

    private boolean slacks;

    private boolean cottonPants;

    private boolean skirt;
}

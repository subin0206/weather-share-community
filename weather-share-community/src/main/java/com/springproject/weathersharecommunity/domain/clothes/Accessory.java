package com.springproject.weathersharecommunity.domain.clothes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Accessory {
    @Id
    @GeneratedValue
    @Column(name = "accessory_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    private boolean earring;

    private boolean muffler;

    private boolean gloves;

    private boolean hat;
}

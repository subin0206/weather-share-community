package com.springproject.weathersharecommunity.domain.clothes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Shoes {
    @Id
    @GeneratedValue
    @Column(name = "shoes_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    private boolean sneakers;

    private boolean loafers;
}

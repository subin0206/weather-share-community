package com.springproject.weathersharecommunity.domain.clothes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Top {
    @Id
    @GeneratedValue
    @Column(name = "top_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    private boolean teeShirt;

    private boolean sweatshirt;

    private boolean onePiece;
}

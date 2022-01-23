package com.springproject.weathersharecommunity.domain.clothes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OuterClothing {
    @Id
    @GeneratedValue
    @Column(name = "outer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    private boolean coat;

    private boolean padding;

    private boolean leatherJacket;
}

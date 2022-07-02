package com.springproject.weathersharecommunity.domain.clothes;

import com.springproject.weathersharecommunity.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Clothes {

    @Id
    @GeneratedValue
    @Column(name = "clothes_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Enumerated(EnumType.STRING)
    private Bottom bottom;

    @Enumerated(EnumType.STRING)
    private Top top;

    @Enumerated(EnumType.STRING)
    private OuterClothing outerClothing;

    @Enumerated(EnumType.STRING)
    private Shoes shoes;

    @Enumerated(EnumType.STRING)
    private Accessory1 accessory1;

    @Enumerated(EnumType.STRING)
    private Accessory2 accessory2;

    @Builder
    public Clothes(Board board, Bottom bottom, Top top, OuterClothing outerClothing, Shoes shoes, Accessory1 accessory1, Accessory2 accessory2) {
        this.board = board;
        this.bottom = bottom;
        this.top = top;
        this.outerClothing = outerClothing;
        this.shoes = shoes;
        this.accessory1 = accessory1;
        this.accessory2 = accessory2;
    }
}

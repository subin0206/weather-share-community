package com.springproject.weathersharecommunity.domain.clothes;

import com.springproject.weathersharecommunity.domain.Board;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Clothes {

    @Id
    @GeneratedValue
    @Column(name = "clothes_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "clothes")
    private List<Top> tops = new ArrayList<>();

    @OneToMany(mappedBy = "clothes")
    private List<Bottom> bottoms = new ArrayList<>();

    @OneToMany(mappedBy = "clothes")
    private List<OuterClothing> outers = new ArrayList<>();

    @OneToOne(mappedBy = "clothes")
    private Shoes shoes;

    @OneToMany(mappedBy = "clothes")
    private List<Accessory> accessories = new ArrayList<>();

}

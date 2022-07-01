package com.springproject.weathersharecommunity.domain.clothes;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum Shoes{

    RunningShoes("운동화"), shoes("구두"), flats("단화"),
    boots("부츠"), slipper("슬리퍼"),  sandal("샌들");
    private String viewName;

    @JsonCreator
    private static Shoes fromValue(String value) {
        switch (value) {
            case "운동화":
                return Shoes.RunningShoes;
            case "구두":
                return Shoes.shoes;
            case "단화":
                return Shoes.flats;
            case "부츠":
                return Shoes.boots;
            case "슬리퍼":
                return Shoes.slipper;
            case "샌들":
                return Shoes.sandal;
        }
        return null;
    }
    Shoes(String viewName) {
        this.viewName = viewName;
    }
}
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Getter @Setter
//public class Shoes {
//    @Id
//    @GeneratedValue
//    @Column(name = "shoes_id")
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "clothes_id")
//    private Clothes clothes;
//
//    private boolean sneakers;
//
//    private boolean loafers;
//}

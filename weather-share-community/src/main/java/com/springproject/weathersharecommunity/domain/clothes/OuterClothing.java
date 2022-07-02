package com.springproject.weathersharecommunity.domain.clothes;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum OuterClothing{

    cardigan("가디건"), jacket("자켓"), coat("코트"),
    jumper("점퍼/야상"), ZipUp("후드집업"), LongPadding("롱패딩")
    , padding("숏패딩"), vest("조끼");
    private String viewName;

    @JsonCreator
    public static OuterClothing fromValue(String value) {
        switch (value) {
            case "가디건":
                return OuterClothing.cardigan;
            case "자켓":
                return OuterClothing.jacket;
            case "코트":
                return OuterClothing.coat;
            case "점퍼/야상":
                return OuterClothing.jumper;
            case "후드집업":
                return OuterClothing.ZipUp;
            case "롱패딩":
                return OuterClothing.LongPadding;
            case "숏패딩":
                return OuterClothing.padding;
            case "조끼":
                return OuterClothing.vest;
        }
        return null;
    }
    OuterClothing(String viewName) {
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
//public class OuterClothing {
//    @Id
//    @GeneratedValue
//    @Column(name = "outer_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "clothes_id")
//    private Clothes clothes;
//
//    private boolean coat;
//
//    private boolean padding;
//
//    private boolean leatherJacket;
//}

package com.springproject.weathersharecommunity.domain.clothes;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Bottom{
    shorts("반바지"), slacks("슬랙스"), BlueJeans("청바지"),
    skirt("스커트"), leggings("레깅스"), LinenPants("린넨바지"),
    TrainingPants("트레이닝 바지");
    private String viewName;

    @JsonCreator
    public static Bottom fromValue(String value) {
        switch (value) {
            case "반바지":
                return Bottom.shorts;
            case "슬랙스":
                return Bottom.slacks;
            case "청바지":
                return Bottom.BlueJeans;
            case "스커트":
                return Bottom.skirt;
            case "레깅스":
                return Bottom.leggings;
            case "린넨바지":
                return Bottom.LinenPants;
            case "트레이닝 바지":
                return Bottom.TrainingPants;
        }
        return null;

    }
    Bottom(String viewName) {
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
//public class Bottom {
//    @Id
//    @GeneratedValue
//    @Column(name = "bottom_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "clothes_id")
//    private Clothes clothes;
//
//    private boolean jeans; //청바지
//
//    private boolean slacks;
//
//    private boolean cottonPants;
//
//    private boolean skirt;
//}

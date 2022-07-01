package com.springproject.weathersharecommunity.domain.clothes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
@Getter
public enum Top{
    TeeShirt("티셔츠"), blouse("블라우스"), shirt("셔츠"),
    sleeveless("민소매"), sweatshirts("맨투맨"), hoodie("후드"),neat("니트/스웨터");

    private String viewName;

    Top(String viewName) {
        this.viewName = viewName;
    }

    @JsonCreator
    public static Top fromValue(String value) {
        switch (value) {
            case "티셔츠":
                return Top.TeeShirt;
            case "블라우스":
                return Top.blouse;
            case "셔츠":
                return Top.shirt;
            case "민소매":
                return Top.sleeveless;
            case "니트/스웨터":
                return Top.neat;
            case "후드":
                return Top.hoodie;
            case "맨투맨":
                return Top.sweatshirts;
        }
        return null;
    }
}
//@Entity
//@Getter @Setter
//public class Top {
//    @Id
//    @GeneratedValue
//    @Column(name = "top_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "clothes_id")
//    private Clothes clothes;
//
//    private boolean teeShirt;
//
//    private boolean sweatshirt;
//
//    private boolean onePiece;
//}

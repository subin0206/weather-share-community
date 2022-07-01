package com.springproject.weathersharecommunity.domain.clothes;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
public enum Accessory1 {
    scarf("목도리"), gloves("장갑"), sunglasses("선글라스")
    , belt("벨트"), hat("모자");
    private String viewName;

    @JsonCreator
    public static Accessory1 fromValue(String value) {
        switch (value) {
            case "목도리":
                return Accessory1.scarf;
            case "장갑":
                return Accessory1.gloves;
            case "선글라스":
                return Accessory1.sunglasses;
            case "벨트":
                return Accessory1.belt;
            case "모자":
                return Accessory1.hat;
        }
        return null;
    }
    Accessory1(String viewName) {
        this.viewName = viewName;
    }
}

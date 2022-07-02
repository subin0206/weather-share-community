package com.springproject.weathersharecommunity.domain.clothes;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Accessory2 {
    scarf("목도리"), gloves("장갑"), sunglasses("선글라스")
    , belt("벨트"), hat("모자");
    private String viewName;

    @JsonCreator
    public static Accessory2 fromValue(String value) {
        switch (value) {
            case "목도리":
                return Accessory2.scarf;
            case "장갑":
                return Accessory2.gloves;
            case "선글라스":
                return Accessory2.sunglasses;
            case "벨트":
                return Accessory2.belt;
            case "모자":
                return Accessory2.hat;
        }
        return null;
    }
    Accessory2(String viewName) {
        this.viewName = viewName;
    }
}

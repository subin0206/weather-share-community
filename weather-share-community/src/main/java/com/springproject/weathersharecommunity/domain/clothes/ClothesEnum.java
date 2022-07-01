package com.springproject.weathersharecommunity.domain.clothes;


import java.util.Arrays;

public enum  ClothesEnum {

    Top("상의", new ClothesTypeOption[]{
            ClothesTypeOption.TeeShirt, ClothesTypeOption.blouse, ClothesTypeOption.neat, ClothesTypeOption.hoodie,
            ClothesTypeOption.shirt, ClothesTypeOption.sleeveless, ClothesTypeOption.sweatshirts
    }),
    Bottom("하의", new ClothesTypeOption[]{
            ClothesTypeOption.BlueJeans, ClothesTypeOption.leggings, ClothesTypeOption.LinenPants, ClothesTypeOption.shorts,
            ClothesTypeOption.skirt, ClothesTypeOption.slacks,ClothesTypeOption.TrainingPants
    }),
    Outer("아우터", new ClothesTypeOption[]{
            ClothesTypeOption.vest, ClothesTypeOption.cardigan, ClothesTypeOption.coat, ClothesTypeOption.jumper, ClothesTypeOption.LongPadding
            , ClothesTypeOption.padding, ClothesTypeOption.jacket, ClothesTypeOption.ZipUp
    }),
    Shoe("신발", new ClothesTypeOption[]{
            ClothesTypeOption.boots, ClothesTypeOption.flats, ClothesTypeOption.RunningShoes, ClothesTypeOption.sandal,
            ClothesTypeOption.shoes, ClothesTypeOption.slipper
    });

    private String viewName;
    private ClothesTypeOption[] option;

    ClothesEnum(String viewName, ClothesTypeOption[] option) {
        this.viewName = viewName;
        this.option = option;
    }

    public static ClothesEnum findGroup(ClothesTypeOption type) {
        return Arrays.stream(ClothesEnum.values())
                .filter(group -> hasType(group, type))
                .findAny()
                .orElseThrow();
    }

    public static boolean hasType(ClothesEnum group, ClothesTypeOption type) {
        return Arrays.stream(group.option)
                .anyMatch(option -> option == type);
    }
}

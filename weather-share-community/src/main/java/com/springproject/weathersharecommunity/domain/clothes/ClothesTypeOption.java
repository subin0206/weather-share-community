package com.springproject.weathersharecommunity.domain.clothes;

import lombok.Getter;

@Getter
public enum ClothesTypeOption {

    TeeShirt("티셔츠"), blouse("블라우스"), shirt("셔츠"),
    sleeveless("민소매"), sweatshirts("맨투맨"), hoodie("후드"),neat("니트/스웨터"),
    shorts("반바지"), slacks("슬랙스"), BlueJeans("청바지"),
    skirt("스커트"), leggings("레깅스"), LinenPants("린넨바지"),
    TrainingPants("트레이닝 바지"),
    cardigan("가디건"), jacket("자켓"), coat("코트"),
    jumper("점퍼/야상"), ZipUp("후드집업"), LongPadding("롱패딩")
    , padding("숏패딩"), vest("조끼"),
    RunningShoes("운동화"), shoes("구두"), flats("단화"),
    boots("부츠"), slipper("슬리퍼"),  sandal("샌들");


    private String viewName;

    ClothesTypeOption(String viewName) {
        this.viewName = viewName;
    }

}

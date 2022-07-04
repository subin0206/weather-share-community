package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.clothes.Accessory1;
import com.springproject.weathersharecommunity.domain.clothes.Accessory2;
import com.springproject.weathersharecommunity.domain.clothes.Clothes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ClothesResponseDto {

    private String top;
    private String bottom;
    private String outerClothing;
    private String shoes;
    private String accessory1;
    private String accessory2;

    public ClothesResponseDto (Clothes entity) {
        this.top = entity.getTop().getViewName();
        this.bottom = entity.getBottom().getViewName();
        this.outerClothing = entity.getOuterClothing().getViewName();
        this.shoes = entity.getShoes().getViewName();
        this.accessory1 = entity.getAccessory1().getViewName();
        this.accessory2 = entity.getAccessory2().getViewName();
    }

}

package com.springproject.weathersharecommunity.Controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter @Setter
@NoArgsConstructor
public class ScrapeSaveRequestDto {
    private Long boardId;
    private Long userId;
}

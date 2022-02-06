package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Image;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.Scrape;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ScrapeListResponseDto {
    private Long id;
    private List<Image> image;
    private Member member;
    private Board board;

    public ScrapeListResponseDto(Scrape entity) {
        this.id = entity.getId();
        this.image = entity.getBoard().getImages();
        this.member = entity.getMember();
        this.board = entity.getBoard();
    }
}

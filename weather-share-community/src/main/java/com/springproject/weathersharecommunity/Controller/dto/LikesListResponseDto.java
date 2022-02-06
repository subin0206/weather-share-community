package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class LikesListResponseDto {
    private int id;
    private List<Image> image;
    private String memberName;
    private Long boardId;

    public LikesListResponseDto(Likes entity) {
        this.id = entity.getId();
        this.image = entity.getBoard().getImages();
        this.memberName = entity.getMember().getUsername();
        this.boardId = entity.getBoard().getId();
    }
}

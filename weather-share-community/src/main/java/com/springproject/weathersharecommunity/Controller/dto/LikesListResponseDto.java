package com.springproject.weathersharecommunity.Controller.dto;

import com.springproject.weathersharecommunity.domain.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class LikesListResponseDto {
    private int id;
    private List<Image> image;
    private String img;
    private String memberName;
    private Long boardId;
    public LikesListResponseDto(Likes entity) {
        this.id = entity.getId();
        this.memberName = entity.getBoard().getMember().getUsername();
        this.img = entity.getBoard().getImages().get(0).getUrl();
        this.boardId = entity.getBoard().getId();
    }
}

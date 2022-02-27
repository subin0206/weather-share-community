package com.springproject.weathersharecommunity.Controller;


import com.springproject.weathersharecommunity.Controller.dto.LikeSaveRequestDto;
import com.springproject.weathersharecommunity.Controller.dto.LikesListResponseDto;
import com.springproject.weathersharecommunity.domain.Likes;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.http.DefaultRes;
import com.springproject.weathersharecommunity.http.ResponseMessage;
import com.springproject.weathersharecommunity.http.StatusCode;
import com.springproject.weathersharecommunity.repository.LikesRepository;
import com.springproject.weathersharecommunity.service.LikesService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikesService likesService;

    @PostMapping("/board/{boardId}/likes")
    public void likes(LikeSaveRequestDto requestDto) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        requestDto.setUserId(member.getId());
        likesService.likes(requestDto);
    }

    @GetMapping("/user/likes")
    public ResponseEntity likesList() {

        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "좋아요 리스트",likesService.likesList()),HttpStatus.OK);
    }

}

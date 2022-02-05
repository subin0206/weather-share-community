package com.springproject.weathersharecommunity.Controller;


import com.springproject.weathersharecommunity.Controller.dto.LikeSaveRequestDto;
import com.springproject.weathersharecommunity.domain.Likes;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.repository.LikesRepository;
import com.springproject.weathersharecommunity.service.LikesService;
import lombok.RequiredArgsConstructor;
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
    public List<Likes> likesList() {
        return likesService.likesList();
    }

}

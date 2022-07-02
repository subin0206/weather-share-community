package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.BoardAllResponseDto;
import com.springproject.weathersharecommunity.Controller.dto.BoardImgResponseDto;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import com.springproject.weathersharecommunity.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberFeedService {

    private final BoardRepository boardRepository;
    private final FollowRepository followRepository;

    // 내가 쓴 글 (개인 피드)
    @Transactional
    public List<BoardAllResponseDto> memberFeed(long memberId) {
        List<BoardAllResponseDto> memberFeedList =
                boardRepository.findByMemberIdOrderByIdDesc(memberId).stream()
                        .map(BoardAllResponseDto::new)
                        .collect(Collectors.toList());
        return memberFeedList;
    }

   //개인 피드 (아이디/사진만)
    @Transactional
    public List<BoardImgResponseDto> memberFeedImg(long memberId) {
        List<BoardImgResponseDto> memberFeedImg =
                boardRepository.findByMemberIdOrderByIdDesc(memberId).stream()
                        .map(BoardImgResponseDto::new)
                        .collect(Collectors.toList());
        return memberFeedImg;
    }

    @Transactional
    public int postCount(long memberId) {
        int postCount = memberFeed(memberId).size();
        return postCount;
    }

}

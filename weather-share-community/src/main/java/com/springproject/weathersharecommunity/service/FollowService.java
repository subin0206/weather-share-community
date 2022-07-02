package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.BoardAllResponseDto;
import com.springproject.weathersharecommunity.Controller.dto.BoardImgResponseDto;
import com.springproject.weathersharecommunity.Controller.dto.FollowListResponseDto;
import com.springproject.weathersharecommunity.Controller.dto.LikesListResponseDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Follow;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import com.springproject.weathersharecommunity.repository.FollowRepository;
import com.springproject.weathersharecommunity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void follow(long fromMemberId, long toMemberId) {
        if(followRepository.findFollowByFromMemberIdAndToMemberId(fromMemberId, toMemberId) != null) throw new RuntimeException("이미 팔로우함");
        followRepository.follow(fromMemberId, toMemberId);
    }

    @Transactional
    public void unFollow(long fromMemberId, long toMemberId) {
        followRepository.unFollow(fromMemberId, toMemberId);
    }


    @Transactional
    public List<FollowListResponseDto> getFollowing(long fromMemberId) {
        List<FollowListResponseDto> followingList =
                followRepository.findAllByFromMemberId(fromMemberId)
                        .stream().map(FollowListResponseDto::new)
                        .collect(Collectors.toList());
        return followingList;
    }

    @Transactional
    public List<Long> getFollowingId(long fromMemberId) {
        List<Long> followList = new ArrayList<>();
        List<Follow> follows = new ArrayList<>();
        follows = followRepository.findToMemberIdByFromMemberId(fromMemberId);
        for (int i = 0; i < follows.size(); i++) {
            followList.add(follows.get(i).getToMember().getId());
        }
        return followList;

    }

  //   팔로우한 멤버 피드 메인에 띄우기
    @Transactional
    public List<BoardImgResponseDto> followingPosts(List<Long> followList) {
        List<BoardImgResponseDto> followingPosts = boardRepository.followingPosts(followList).stream()
                .map(BoardImgResponseDto::new)
                .collect(Collectors.toList());
        return followingPosts;
    }

    @Transactional
    public int followCount (List<Long> followList) {
        int followCount = followList.size();
        return followCount;
    }
}





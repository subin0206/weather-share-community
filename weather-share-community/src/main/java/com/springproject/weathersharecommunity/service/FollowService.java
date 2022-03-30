package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.FollowListResponseDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.repository.FollowRepository;
import com.springproject.weathersharecommunity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;
    private final EntityManager em;

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
    public List<FollowListResponseDto> getFollower(long toMemberId)  {
        List<FollowListResponseDto> followerList =
                followRepository.findAllByFromMemberId(toMemberId)
                        .stream().map(FollowListResponseDto::new)
                        .collect(Collectors.toList());
        return followerList;
    }

    @Transactional
    public List<FollowListResponseDto> getFollowing(long fromMemberId) {
        List<FollowListResponseDto> followingList =
                followRepository.findAllByFromMemberId(fromMemberId)
                        .stream().map(FollowListResponseDto::new)
                        .collect(Collectors.toList());
        return followingList;
    }

    // 팔로우한 멤버 피드 메인에 띄우기
    @Transactional
    public List<Board> followingPosts() {
        return followRepository.followingPosts();
    }


}





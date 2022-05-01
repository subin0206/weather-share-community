package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.FollowSaveRequestDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.http.DefaultRes;
import com.springproject.weathersharecommunity.repository.FollowRepository;
import com.springproject.weathersharecommunity.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class FollowController {

    private final FollowRepository followRepository;
    private final FollowService followService;

    // 다른 사람 아이디를 팔로우 하는 기능
    @PostMapping("/follow/{toMemberId}")
    public ResponseEntity followMember(FollowSaveRequestDto followSaveRequestDto) {

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        followSaveRequestDto.setFromMemberId(member.getId());

        followService.follow(followSaveRequestDto.getFromMemberId(), followSaveRequestDto.getToMemberId() );
        return new ResponseEntity(DefaultRes.defaultRes(com.springproject.weathersharecommunity.http.StatusCode.OK, "팔로우 성공"), HttpStatus.OK);
    }


    @DeleteMapping("/follow/{toMemberId}")
    public ResponseEntity unfollowMember (FollowSaveRequestDto followSaveRequestDto) {

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        followSaveRequestDto.setFromMemberId(member.getId());

        followService.unFollow(followSaveRequestDto.getFromMemberId(), followSaveRequestDto.getToMemberId() );
        return new ResponseEntity(DefaultRes.defaultRes(com.springproject.weathersharecommunity.http.StatusCode.OK, "언팔로우 성공"), HttpStatus.OK);

    }

    // 나를 팔로우하는 사람들 리스트
    @GetMapping("/follow/getFollower/{toMemberId}")
    public ResponseEntity getFollower(@PathVariable long toMemberId) {
        return new ResponseEntity(DefaultRes.defaultRes(com.springproject.weathersharecommunity.http.StatusCode.OK, "팔로우 리스트", followService.getFollower(toMemberId)), HttpStatus.OK);
    }

   /* public List<FollowListResponseDto> getFollower(@PathVariable long toMemberId) {
        return followService.getFollower(toMemberId);
    } */

    //내가 팔로우하는 사람들 리스트
    @GetMapping("/follow/getFollowing")
    public ResponseEntity getFollowing() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();

        return new ResponseEntity(DefaultRes.defaultRes(com.springproject.weathersharecommunity.http.StatusCode.OK, "팔로잉 리스트", followService.getFollowing(member.getId())), HttpStatus.OK);
    }

    /*public List<FollowListResponseDto> getFollowing(@PathVariable long fromMemberId) {
        return followService.getFollowing(fromMemberId);
    } */

    @GetMapping("boards/main")
    public ResponseEntity FollowingPosts(FollowSaveRequestDto followSaveRequestDto) {
        followService.followingPosts();

        return new ResponseEntity(DefaultRes.defaultRes(com.springproject.weathersharecommunity.http.StatusCode.OK, "팔로우 피드"), HttpStatus.OK);
    }

}

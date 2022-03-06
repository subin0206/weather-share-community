package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.LikeSaveRequestDto;
import com.springproject.weathersharecommunity.Controller.dto.LikesListResponseDto;
import com.springproject.weathersharecommunity.Controller.dto.ScrapeListResponseDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Likes;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import com.springproject.weathersharecommunity.repository.LikesRepository;
import com.springproject.weathersharecommunity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void likes(LikeSaveRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾지 못했습니다."));
        Board board = boardRepository.findOne(requestDto.getBoardId());
        Optional<Likes> likesPost = likesRepository.findByBoardAndMember(board, member);
        likesPost.ifPresentOrElse(
                likes -> {
                    likesRepository.delete(likes);
                    board.discountLike(likes);
                },
                () -> {
                    Likes likes = Likes.builder().build();
                    likes.mappingBoard(board);
                    likes.mappingMember(member);
                    board.updateLikeCount();
                    likesRepository.save(likes);
                }

        );
//        likesRepository.save(new Likes(board,member));
    }

    @Transactional(readOnly = true)
    public List<LikesListResponseDto> likesList() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();

        List<LikesListResponseDto> list = likesRepository.findAllByMemberId(member.getId())
                .stream().map(LikesListResponseDto::new)
                .collect(Collectors.toList());
        return list;
    }

//    @Transactional(readOnly = true)
//    public List<Likes> likesList() {
//        Authentication user = SecurityContextHolder.getContext().getAuthentication();
//        Member member = (Member) user.getPrincipal();
////        List<LikesListResponseDto> list = likesRepository.findAllByMemberId(member.getId())
////                .stream().map(LikesListResponseDto::new)
////                .collect(Collectors.toList());
//        List<Likes> list = likesRepository.findAllByMemberId(member.getId());
//        return list;
//    }

}

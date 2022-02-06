package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.ScrapeListResponseDto;
import com.springproject.weathersharecommunity.Controller.dto.ScrapeSaveRequestDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Likes;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.Scrape;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import com.springproject.weathersharecommunity.repository.MemberRepository;
import com.springproject.weathersharecommunity.repository.ScrapeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScrapeService {

    private final ScrapeRepository scrapeRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void scrape(ScrapeSaveRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾지 못했습니다."));
        Board board = boardRepository.findOne(requestDto.getBoardId());

        Optional<Scrape> scrapePost = scrapeRepository.findByBoardAndMember(board, member);
        scrapePost.ifPresentOrElse(
                scrape -> {
                    scrapeRepository.delete(scrape);
                },
                () -> {
                    scrapeRepository.save(new Scrape(board, member));
                }

        );
    }

    @Transactional
    public List<ScrapeListResponseDto> scrapeList() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        List<ScrapeListResponseDto> list = scrapeRepository.findAllByMember(member)
                .stream().map(ScrapeListResponseDto::new)
                .collect(Collectors.toList());
        return  list;
    }
}

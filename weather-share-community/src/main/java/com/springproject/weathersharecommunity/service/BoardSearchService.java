package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.BoardAllResponseDto;
import com.springproject.weathersharecommunity.Controller.dto.BoardImgResponseDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BoardSearchService {

    private final BoardRepository boardRepository;

    //글 검색
    @Transactional
    public List<BoardAllResponseDto> searchPosts(String keyword) {
        return boardRepository.findAllSearch(keyword).stream()
                .map(BoardAllResponseDto::new)
                .collect(Collectors.toList());
    }

    //글 검색(사진)
    @Transactional
    public List<BoardImgResponseDto> searchPostsImg(String keyword) {
        return boardRepository.findAllSearch(keyword).stream()
                .map(BoardImgResponseDto::new)
                .collect(Collectors.toList());
    }
}


package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.BoardSearchDto;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BoardSearchService {

    BoardRepository boardRepository;

//    //글 검색
//    @Transactional
//    public List<BoardSearchDto> searchPosts(String keyword) {
//        return boardRepository.findAllSearch(keyword).stream()
//                .map(BoardSearchDto::new)
//                .collect(Collectors.toList());
//    }
}


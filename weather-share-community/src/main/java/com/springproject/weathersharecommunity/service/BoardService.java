package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    //글 쓰기
    public long write(Board board){
        boardRepository.save(board);
        return board.getId();
    }

    
}

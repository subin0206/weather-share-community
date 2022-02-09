package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.BoardEditRequestDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;

@Service
@Transactional
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    //글 쓰기
    public long create(Board board){
        boardRepository.save(board);
        return board.getId();
    }

    //글 수정
    @Transactional
    public void updateBoard(BoardEditRequestDto dto){
        Board board = boardRepository.findOne(dto.getBoardId());
        board.setContent(dto.getContent());
        board.setPrivacy(dto.isPrivacy());
    }

    //전체 회원 조회
    public List<Board> findBoards(){
        return boardRepository.findAll();
    }

    //특정 회원 조회
    public Board findOne(Long boardId){
        return boardRepository.findOne(boardId);
    }

    //특정 글 삭제
    @Transactional
    public void delete(Board board){
        boardRepository.deleteOne(board);
    }
    
}

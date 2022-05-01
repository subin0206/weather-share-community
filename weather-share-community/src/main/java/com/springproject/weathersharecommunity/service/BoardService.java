package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.*;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import com.springproject.weathersharecommunity.repository.BoardRepositoryTest;
import com.springproject.weathersharecommunity.repository.ImageRepository;
import com.springproject.weathersharecommunity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final S3FileUploadService fileUploadService;

    @Autowired
    BoardRepository boardRepository;

    private final BoardRepositoryTest repositoryTest;

    private final ImageRepository imageRepository;

    private final MemberRepository memberRepository;

    //글 쓰기
    public long create(Board board, List<MultipartFile> images){
        boardRepository.save(board);
        board.setImages(fileUploadService.uploadImage(images,board ));

        return board.getId();
    }

    //글 수정
    @Transactional
    public void updateBoard(BoardEditRequestDto dto){
        Board board = boardRepository.findOne(dto.getBoardId());
        board.setContent(dto.getContent());
        board.setPrivacy(dto.isPrivacy());
    }

    //전체 글 조회
    public List<Board> findBoards(){

        return boardRepository.findAll();
    }

    //특정 글 조회
    public Board findOne(Long boardId){

        return boardRepository.findOne(boardId);
    }

    //특정 글 삭제
    @Transactional
    public void delete(Board board){

        boardRepository.deleteOne(board);
    }

    @Transactional
    public BoardResponseDto boardDetail(Long boardId) {
        Board board = repositoryTest.findById(boardId).orElseThrow(() -> new IllegalArgumentException("글을 찾을수 없습니다."));
        List<String> images = imageRepository.findUrlByBoardId(boardId);
        BoardResponseDto boardResponseDto = new BoardResponseDto(board, images, null);
        Long memberId = board.getMember().getId();
        Member entity = memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다."));
        MemberResponseDto memberResponseDto = new MemberResponseDto(entity);
        boardResponseDto.setMemberResponseDto(memberResponseDto);

        return boardResponseDto;
    }

    @Transactional
    public void save(BoardRequestDto requestDto, List<MultipartFile> images){

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        requestDto.setMember(member);
        Board board1 = repositoryTest.save(requestDto.toEntity());

        board1.setImages(fileUploadService.uploadImage(images,board1));

    }

    @Transactional
    public List<BoardAllResponseDto> findAllBoard() {
        List<Board> boards = repositoryTest.findAll();
        List<BoardAllResponseDto> list = new ArrayList<>();
        try{
        list = boards
                .stream().map(BoardAllResponseDto::new)
                .collect(Collectors.toList());

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return list;

    }

}

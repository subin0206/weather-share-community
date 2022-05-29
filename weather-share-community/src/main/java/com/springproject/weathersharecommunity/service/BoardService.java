package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.*;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import com.springproject.weathersharecommunity.repository.ImageRepository;
import com.springproject.weathersharecommunity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final S3FileUploadService fileUploadService;

    private final BoardRepository boardRepository;

    private final ImageRepository imageRepository;

    private final MemberRepository memberRepository;


    @Transactional
    public BoardResponseDto boardDetail(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("글을 찾을수 없습니다."));
        List<String> images = imageRepository.findUrlByBoardId(boardId);
        BoardResponseDto boardResponseDto = new BoardResponseDto(board, images, null);
        Long memberId = board.getMember().getId();
        Member entity = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        MemberResponseDto memberResponseDto = new MemberResponseDto(entity);
        boardResponseDto.setMemberResponseDto(memberResponseDto);

        return boardResponseDto;
    }

    @Transactional
    public void save(BoardRequestDto requestDto, List<MultipartFile> images) {

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        requestDto.setMember(member);
        Board board1 = boardRepository.save(requestDto.toEntity());
    }

    @Transactional
    public List<BoardAllResponseDto> findAllBoard() {
        List<Board> boards = boardRepository.findAll();
        List<BoardAllResponseDto> list = new ArrayList<>();
        try {
            list = boards
                    .stream().map(BoardAllResponseDto::new)
                    .collect(Collectors.toList());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return list;

    }

    @Transactional
    public void updateScore() {
        List<Board> boards = boardRepository.findAll();
        for (int i = 0; i < boards.size(); i++) {
            boards.get(i).countScore(boards.get(i));
        }
    }

    @Transactional
    public List<BoardAllResponseDto> popularBoards() {
        List<BoardAllResponseDto> boards = boardRepository.findAllByOrderByScoreDesc()
                .stream()
                .map(BoardAllResponseDto::new)
                .collect(Collectors.toList());
        return boards;
    }


}

package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.ReplyListResponseDto;
import com.springproject.weathersharecommunity.Controller.dto.ReplySaveRequestDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.Reply;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import com.springproject.weathersharecommunity.repository.MemberRepository;
import com.springproject.weathersharecommunity.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long replyWrite(ReplySaveRequestDto requestDto, Long boardId) {

        Board board = boardRepository.findById(boardId).orElseThrow(()->new IllegalArgumentException("글을 찾지 못했습니다."));
        requestDto.setBoard(board);
        Reply reply = replyRepository.save(requestDto.toEntity());
        return reply.getId();
    }

    @Transactional
    public String replyDelete(Long replyId) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new IllegalArgumentException("댓글 아이디를 찾지 못했습니다."));

        replyRepository.delete(reply);
        return "delete";
    }

    @Transactional
    public List<ReplyListResponseDto> replyList(Long boardId) {
        List<ReplyListResponseDto> reply = replyRepository.findAllByBoardId(boardId)
                .stream().map(ReplyListResponseDto::new)
                .collect(Collectors.toList());
        return reply;
    }


}

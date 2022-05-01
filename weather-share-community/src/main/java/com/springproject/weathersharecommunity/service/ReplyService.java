package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.ReplySaveRequestDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Reply;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import com.springproject.weathersharecommunity.repository.MemberRepository;
import com.springproject.weathersharecommunity.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long replyWrite(ReplySaveRequestDto requestDto, Long boardId) {
//        Member findMember = memberRepository.findById(requestDto.getMember().getId())
//                .orElseThrow(()-> new IllegalArgumentException("회원을 찾지 못했습니다."));
        Board board = boardRepository.findById(boardId).orElseThrow(()->new IllegalArgumentException("글을 찾지 못했습니다."));
        requestDto.setBoard(board);
        Reply reply = replyRepository.save(requestDto.toEntity());
        return reply.getId();
    }

    public String replyDelete(Long replyId) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new IllegalArgumentException("댓글 아이디를 찾지 못했습니다."));

        replyRepository.delete(reply);
        return "delete";
    }
}

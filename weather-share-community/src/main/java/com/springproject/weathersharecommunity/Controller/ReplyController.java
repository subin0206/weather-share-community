package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.ReplyAgainRequestDto;
import com.springproject.weathersharecommunity.Controller.dto.ReplySaveRequestDto;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.http.DefaultRes;
import com.springproject.weathersharecommunity.http.StatusCode;
import com.springproject.weathersharecommunity.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping(value = "/board/{boardId}/reply",consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity replyWrite(@RequestBody ReplySaveRequestDto requestDto, @PathVariable Long boardId) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        requestDto.setMember(member);
        replyService.replyWrite(requestDto, boardId);

        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "댓글달기",null), HttpStatus.OK);
    }
    @PostMapping(value = "/board/{boardId}/reply/{replyId}",consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity reply2Write(@RequestBody ReplyAgainRequestDto requestDto, @PathVariable Long boardId, @PathVariable Long replyId) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        requestDto.setMember(member);
        replyService.replyToReply(requestDto, boardId,replyId);

        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "답글달기",null), HttpStatus.OK);
    }
    @GetMapping(value = "/board/{boardId}/replies")
    public ResponseEntity replyList(@PathVariable Long boardId) {
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "댓글 목록", replyService.replyList(boardId)), HttpStatus.OK);
    }
    @GetMapping(value = "/reply/{replyId}")
    public ResponseEntity reply2List(@PathVariable Long replyId) {
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "댓글 목록", replyService.reply2List(replyId)), HttpStatus.OK);
    }
    @PostMapping("/board/reply/{replyId}")
    public ResponseEntity replyDelete(@PathVariable Long replyId) {
        replyService.replyDelete(replyId);
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "댓글삭제", replyService.replyDelete(replyId)), HttpStatus.OK);
    }
}

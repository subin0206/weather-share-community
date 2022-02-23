package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.ReplySaveRequestDto;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/board/{boardId}/reply")
    public Long replyWrite(@RequestBody ReplySaveRequestDto requestDto, @PathVariable Long boardId) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        requestDto.setMember(member);
        return replyService.replyWrite(requestDto, boardId);
    }

    @PostMapping("/board/reply/{replyId}")
    public void replyDelete(@PathVariable Long replyId) {
        replyService.replyDelete(replyId);
    }
}

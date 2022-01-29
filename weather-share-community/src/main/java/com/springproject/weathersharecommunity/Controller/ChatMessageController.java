package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.ChatMessageDto;
import com.springproject.weathersharecommunity.domain.ChatMessage;
import com.springproject.weathersharecommunity.domain.ChatRoom;
import com.springproject.weathersharecommunity.domain.ChatRoomJoin;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.service.ChatService;
import com.springproject.weathersharecommunity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MemberService memberService;
    private String userName;

    @GetMapping("/chat")
    public String chatHome(Model model) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        String userName = member.getUsername();
        Member  currentMember = memberService.findByName(userName);
        List<ChatRoomJoin> chatRoomJoins = chatService.findByUser(currentMember);
        if (currentMember == null) {
            model.addAttribute("userName", "");
            model.addAttribute("userId", 0);
        }
        else{
            model.addAttribute("userName", currentMember.getUsername());
            model.addAttribute("userId", currentMember.getId());
        }
        return "chat/main";
    }
    @PostMapping("/chat/newChat")
    public String newChat(@RequestParam("receiver") String user1, @RequestParam("sender") String user2) {
        Long chatRoomId = chatService.newRoom(user1, user2);
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getDetails();
        userName = member.getUsername();
        return "redirect:/personalChat/"+chatRoomId;
    }

    @RequestMapping("/personalChat/{chatRoomId}")
    public String goChat(@PathVariable("chatRoomId") Long chatRoomId, Model model) {
        ChatRoom chatRoom = chatService.findById(chatRoomId);
        List<ChatMessage> messages = chatRoom.getMessageList();
        Collections.sort(messages, (t1, t2)-> {
            if(t1.getId() > t2.getId())
                return -1;
                else return 1;
        });
        if (userName == null) {
            model.addAttribute("userName", "");
            model.addAttribute("userId",0);
        } else{
            model.addAttribute("userName", userName);
            model.addAttribute("userId", memberService.findByName(userName).getId());
        }
        List<ChatRoomJoin> list = chatService.findByChatRoom(chatRoom);
        model.addAttribute("messages", messages);
        model.addAttribute("nickname", userName);
        model.addAttribute("chatRoomId", chatRoomId);

        int cnt = 0;
        for (ChatRoomJoin join : list) {
            if (join.getMember().getUsername().equals(userName) == false) {
                ++cnt;
            }
        }
        if (cnt >= 2) {
            return "redirect:/chat";
        }
        if (cnt == 0) {
            model.addAttribute("receiver","");
        }
        return "chat/chatRoom";
    }
    @MessageMapping("/chat/send")
    public void sendMsg(ChatMessageDto messageDto) throws Exception {
        String receiver = messageDto.getReceiver();
        chatService.save(messageDto);
        simpMessagingTemplate.convertAndSend("/topic/" + receiver, messageDto);

    }

}

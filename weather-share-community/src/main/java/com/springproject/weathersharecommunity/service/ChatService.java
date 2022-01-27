package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.ChatMessageDto;
import com.springproject.weathersharecommunity.Controller.dto.ChatRoomDto;
import com.springproject.weathersharecommunity.domain.ChatMessage;
import com.springproject.weathersharecommunity.domain.ChatRoom;
import com.springproject.weathersharecommunity.domain.ChatRoomJoin;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.repository.ChatMessageRepository;
import com.springproject.weathersharecommunity.repository.ChatRoomJoinRepository;
import com.springproject.weathersharecommunity.repository.ChatRoomRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
@Getter
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomJoinRepository chatRoomJoinRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final MemberService memberService;

    @Transactional
    public void save(ChatMessageDto messageDto) {
        ChatMessage chatMessage = new ChatMessage(messageDto.getMessage(), LocalDateTime.now(),
                findById(messageDto.getChatRoomId()),memberService.findByName(messageDto.getSender()));
        chatMessageRepository.save(chatMessage);

    }
    @Transactional
    public Long newRoom(String user1, String user2) {
        Long ret = check(user1, user2);
        if (ret != 0) {
            return ret;
        }
        ChatRoom chatRoom = new ChatRoom();
        ChatRoom newChatRoom = chatRoomRepository.save(chatRoom);
        if (user1.equals(user2)) {
            createRoom(user1, newChatRoom);
        } else {
            createRoom(user1, newChatRoom);
            createRoom(user2, newChatRoom);
        }
        return newChatRoom.getId();
    }

    @Transactional
    public void createRoom(String user, ChatRoom chatRoom) {
        ChatRoomJoin chatRoomJoin = new ChatRoomJoin();
        chatRoomJoin.setChatRoom(chatRoom);
        chatRoomJoin.setMember(memberService.findByName(user));
        chatRoomJoinRepository.save(chatRoomJoin);
    }

    @Transactional(readOnly = true)
    public Long check(String user1, String user2) {
        Member member1 = memberService.findByName(user1);
        List<ChatRoomJoin> list1 = chatRoomJoinRepository.findByMember(member1);
        Set<ChatRoom> set1 = new HashSet<>();
        for (ChatRoomJoin chatRoomJoin : list1) {
            set1.add(chatRoomJoin.getChatRoom());
        }
        Member member2 = memberService.findByName(user2);
        List<ChatRoomJoin> list2 = chatRoomJoinRepository.findByMember(member2);
        for (ChatRoomJoin chatRoomJoin : list2) {
            if (set1.contains(chatRoomJoin.getChatRoom())) {
                return chatRoomJoin.getChatRoom().getId();
            }
        }
        return 0L;

    }

    @Transactional(readOnly = true)
    public ChatRoom findById(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(()->new IllegalArgumentException("방을 찾을 수 업습니다."));
       return chatRoom;
    }

    @Transactional(readOnly = true)
    public List<ChatRoomJoin> findByChatRoom(ChatRoom chatRoom) {
       List<ChatRoomJoin> chatRoomJoin = chatRoomJoinRepository.findByChatRoom(chatRoom);

        return chatRoomJoin;
    }

    @Transactional
    public void delete(ChatRoomJoin chatRoomJoin) {
        chatRoomJoinRepository.delete(chatRoomJoin);
    }

    @Transactional
    public String findAnotherUser(ChatRoom chatRoom, String name) {
        List<ChatRoomJoin> chatRoomJoins = findByChatRoom(chatRoom);
        for (ChatRoomJoin chatRoomJoin : chatRoomJoins) {
            if (name.equals(chatRoomJoin.getMember().getUsername()) == false) {
                return chatRoomJoin.getMember().getUsername();
            }
        }
        return name;
    }
    @Transactional(readOnly = true)
    public List<ChatRoomJoin> findByUser(Member member) {
        return chatRoomJoinRepository.findByMember(member);
    }

    public List<ChatRoomDto> setting(List<ChatRoomJoin> chatRoomJoins, Member member) {
        List<ChatRoomDto> chatRoomDtos = new ArrayList<>();
        for (ChatRoomJoin temp : chatRoomJoins) {
            ChatRoomDto chatRoomDto = new ChatRoomDto();
            ChatRoom chatRoom = temp.getChatRoom();
            chatRoomDto.setId(chatRoom.getId());
            if (chatRoom.getMessageList().size() != 0) {
                Collections.sort(chatRoom.getMessageList(), new Comparator<ChatMessage>() {
                    @Override
                    public int compare(ChatMessage o1, ChatMessage o2) {
                        if(o1.getTime().isAfter(o2.getTime()))
                            return -1;
                        else
                            return 1;
                    }
                });
                ChatMessage lastMessage = chatRoom.getMessageList().get(0);
                chatRoomDto.makeChatRoom(lastMessage.getMessage(), findAnotherUser(chatRoom, member.getUsername()),lastMessage.getTime());
                chatRoomDtos.add(chatRoomDto);
            }
            else{
                delete(temp);
            }
        }
        Collections.sort(chatRoomDtos, new Comparator<ChatRoomDto>() {
            @Override
            public int compare(ChatRoomDto o1, ChatRoomDto o2) {
                if(o1.getTime().isAfter(o2.getTime()))
                    return -1;
                else
                    return 1;
            }
        });
        return chatRoomDtos;
    }

}

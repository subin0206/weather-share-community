package com.springproject.weathersharecommunity.service;

import com.springproject.weathersharecommunity.Controller.dto.MemberSaveRequestDto;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.jwt.SecurityUtil;
import com.springproject.weathersharecommunity.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Getter
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(MemberSaveRequestDto requestDto){
//        duplicationMember(requestDto);
        requestDto.setPwd(passwordEncoder.encode(requestDto.getPwd()));
        Member member = memberRepository.save(requestDto.toEntity());

        return member.getId();
    }


//    public void duplicationMember(MemberSaveRequestDto requestDto){
//        Optional<Member> checkMember = memberRepository.findByEmail(requestDto.getEmail());
//        if (checkMember.isPresent()) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//    }
}

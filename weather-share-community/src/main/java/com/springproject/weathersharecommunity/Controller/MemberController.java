package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.Controller.dto.ApiResponse;
import com.springproject.weathersharecommunity.Controller.dto.MemberSaveRequestDto;
import com.springproject.weathersharecommunity.jwt.JwtTokenProvider;
import com.springproject.weathersharecommunity.repository.MemberRepository;
import com.springproject.weathersharecommunity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @PostMapping("user/join")
    @ResponseBody
    public Long join(@Validated @RequestBody MemberSaveRequestDto requestDto){

        System.out.println("bbbbbbbb" + requestDto.getPwd());
        return memberService.save(requestDto);
    }

    @PostMapping("user/login")
    @ResponseBody
    public String login(@RequestBody Map<String, String> user) {
        Member member = memberRepository.findByUserName(user.get("userName"))
                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 아이디입니다."));
        if(!passwordEncoder.matches(user.get("pwd"),member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());

    }

}

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String login(@RequestBody MemberSaveRequestDto requestDto, HttpServletResponse response) {
        Member member = memberRepository.findByUserName(requestDto.getUserName())
                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 아이디입니다."));
        if(!passwordEncoder.matches(requestDto.getPwd(),member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        System.out.println(member.getUsername()+" member user name");
        String token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
        response.setHeader("X-AUTH-TOKEN", token);
        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return token;
    }

    @PostMapping("user/logout")
    public void logout(HttpServletResponse response){
        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    @GetMapping("user/info")
    public String info(){
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        return user.getAuthorities().toString() + "/" + member.getUserEmail();
    }
}

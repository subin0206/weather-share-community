package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.MemberResponseDto;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.Controller.dto.ApiResponse;
import com.springproject.weathersharecommunity.Controller.dto.MemberSaveRequestDto;
import com.springproject.weathersharecommunity.http.DefaultRes;
import com.springproject.weathersharecommunity.http.ResponseMessage;
import com.springproject.weathersharecommunity.http.StatusCode;
import com.springproject.weathersharecommunity.jwt.JwtTokenProvider;
import com.springproject.weathersharecommunity.repository.MemberRepository;
import com.springproject.weathersharecommunity.service.MemberFeedService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final MemberFeedService memberFeedService;

    @PostMapping("user/join")
    @ResponseBody
    public ResponseEntity join(@Validated @RequestPart(value = "requestDto",required = false) MemberSaveRequestDto requestDto, @RequestPart(value = "profile", required = false) final MultipartFile multipartFile) throws IOException {

        System.out.println("bbbbbbbb" + requestDto.getPwd());
        memberService.save(requestDto, multipartFile);
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.NOT_FOUND, "회원가입 성공"), HttpStatus.OK);
    }

    @PostMapping("user/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody MemberSaveRequestDto requestDto, HttpServletResponse response) {
        Member member = memberService.findByUserName(requestDto);

        if(!passwordEncoder.matches(requestDto.getPwd(),member.getPassword())){
            return new ResponseEntity(DefaultRes.defaultRes(StatusCode.NOT_FOUND, ResponseMessage.PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }
        System.out.println(member.getUsername()+" member user name");
        String token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
        response.setHeader("X-AUTH-TOKEN", token);
        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, token), HttpStatus.OK);
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

    @GetMapping("user/mypage")
    public ResponseEntity myPage() {

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "마이페이지", memberService.myPage(member.getId())),HttpStatus.OK);
    }
    @GetMapping("confirm-email")
    public String viewConfirmEmail(@Valid @RequestParam String token) {
        memberService.confirmEmail(token);
        return "redirect:/test";
    }

    @PostMapping("user/mypage/edit/profile")
    public ResponseEntity ProfileUpdate(@RequestPart(value = "profile", required = false) final MultipartFile multipartFile) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "마이페이지 수정 완료", memberService.ProfileImgUpdate(member.getId(), multipartFile)),HttpStatus.OK);
    }

    //개인 피드
    @GetMapping("user/memberFeed")
    public ResponseEntity memberFeed() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "개인 피드", memberFeedService.memberFeed(member.getId())),HttpStatus.OK);
    }


}

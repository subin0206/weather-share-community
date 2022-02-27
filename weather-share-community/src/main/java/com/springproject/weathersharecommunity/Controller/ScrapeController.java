package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.ScrapeListResponseDto;
import com.springproject.weathersharecommunity.Controller.dto.ScrapeSaveRequestDto;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.domain.Scrape;
import com.springproject.weathersharecommunity.http.DefaultRes;
import com.springproject.weathersharecommunity.http.StatusCode;
import com.springproject.weathersharecommunity.service.ScrapeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScrapeController {

    private final ScrapeService scrapeService;

    @PostMapping("/user/{boardId}/scrape")
    public ResponseEntity scrapes(ScrapeSaveRequestDto requestDto) {

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();
        requestDto.setUserId(member.getId());

        scrapeService.scrape(requestDto);
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "스크랩 성공"), HttpStatus.OK);
    }

    @GetMapping("/user/scrape/list")
    public ResponseEntity scrapeList() {

        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "스크랩 리스트", scrapeService.scrapeList()), HttpStatus.OK);

    }

}

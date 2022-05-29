package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.BoardRequestDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.http.DefaultRes;
import com.springproject.weathersharecommunity.http.StatusCode;
import com.springproject.weathersharecommunity.repository.BoardRepository;
import com.springproject.weathersharecommunity.service.BoardSearchService;
import com.springproject.weathersharecommunity.service.BoardService;
import com.springproject.weathersharecommunity.service.S3FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@ConfigurationProperties(prefix="server.upload")
public class BoardController {

    private final BoardService boardService;
    private final BoardSearchService boardSearchService;

    private final S3FileUploadService fileUploadService;


    //글 하나 조회
    private final BoardRepository boardRepository;
  
    @GetMapping(value = "/board/{boardId}")
    public ResponseEntity boardInfo(@PathVariable("boardId") Long boardId){
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "글 성공", boardService.boardDetail(boardId)), HttpStatus.OK);
    }

    @PostMapping(value = "/board/save")
    public ResponseEntity boardSave(@RequestPart BoardRequestDto requestDto,@RequestPart(required = false) List<MultipartFile> images){
        boardService.save(requestDto, images);
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "글 올리기 성공"), HttpStatus.OK);
    }

    @GetMapping(value = "/boards")
    public ResponseEntity boardAll() {
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "전체 글 조회", boardService.findAllBoard()), HttpStatus.OK);
    }

    @GetMapping(value = "/test/boards")
    public String test() {
        boardService.updateScore();
        return "성공";
    }
    @GetMapping(value = "/boards/popular")
    public ResponseEntity popularBoards() {
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "인기글", boardService.popularBoards()), HttpStatus.OK);
    }
//     //글 검색
//     @GetMapping(value = "/boards/{boardId}/search")
//     public String search(@RequestParam(value = "keyword") String keyword, Model model) {


//         model.addAttribute("searchList", boardSearchService.searchPosts(keyword));

//         return "searchList";
//     }

}


package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.BoardRequestDto;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@ConfigurationProperties(prefix="server.upload")
public class BoardController {

    private final BoardService boardService;
    private final BoardSearchService boardSearchService;

    private final S3FileUploadService fileUploadService;


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


//     //글 검색
//     @GetMapping(value = "/boards/{boardId}/search")
//     public String search(@RequestParam(value = "keyword") String keyword, Model model) {


//         model.addAttribute("searchList", boardSearchService.searchPosts(keyword));

//         return "searchList";
//     }

}


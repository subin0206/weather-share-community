package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.BoardEditRequestDto;
import com.springproject.weathersharecommunity.Controller.dto.BoardRequestDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Image;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.http.DefaultRes;
import com.springproject.weathersharecommunity.http.StatusCode;
import com.springproject.weathersharecommunity.repository.BoardInfoMapping;
import com.springproject.weathersharecommunity.repository.BoardRepositoryTest;
import com.springproject.weathersharecommunity.service.BoardService;
import com.springproject.weathersharecommunity.service.S3FileUploadService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@ConfigurationProperties(prefix="server.upload")
public class BoardController {

    private final BoardService boardService;

    private final S3FileUploadService fileUploadService;

    private final BoardInfoMapping boardInfoMapping;

    private final BoardRepositoryTest boardRepositoryTest;

//    @GetMapping("/boards/new")
//    public String createForm(Model model) {
//        model.addAttribute("BoardRequestDto", new BoardRequestDto());
//        return "board/createBoardForm";
//    }

    //글 등록
    @PostMapping("/boards/new")

    public ResponseEntity<Board> create(@Valid @RequestPart BoardRequestDto boardRequestDto, @RequestPart(required = false) List<MultipartFile> images) {

        Board board = new Board();

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();

//       fileUploadService.uploadImage(images);

        board.setMember(member);
        board.setContent(boardRequestDto.getContent());
        board.setCreateDate(boardRequestDto.getCreateDate());
        board.setStatus(boardRequestDto.getStatus());
        board.setPrivacy(boardRequestDto.isPrivacy());

        board.setLowestTemperature(boardRequestDto.getLowestTemperature());
        board.setPresentTemperature(boardRequestDto.getPresentTemperature());
        board.setHighestTemperature(boardRequestDto.getHighestTemperature());

        List<Image> imageList = fileUploadService.uploadImage(images);
        board.setImages(imageList);
//        board.setImages(fileUploadService.uploadImage(images));
//        boardService.create(board);

//         for(Image image: imageList){
//             image.setBoard(board);
//         }
//        board.setImages(fileUploadService.uploadImage(images));

        boardService.create(board, images);

        /*
        List -> Json
         */
//        List<Image> listImages = board.getImages();
//        String jsonImages = new Gson().toJson(listImages);
//        System.out.println(jsonImages);
//        return board;
        return new ResponseEntity(DefaultRes.defaultRes(StatusCode.OK, "성공"), HttpStatus.OK);

    }

    //글 전체 조회
    @GetMapping(value = "/boards")
    public ResponseEntity list(Model model) {
        List<Board> boards = boardService.findBoards();
        model.addAttribute("boards", boards);
        return new ResponseEntity(boards, HttpStatus.OK);
//        return boards;
    }

    //글 하나 조회
    @GetMapping(value = "/board/{boardId}")
    public ResponseEntity selectBoard(@PathVariable("boardId") Long boardId){
        Board board = boardService.findOne(boardId);

        return new ResponseEntity(board, HttpStatus.OK);
    }


    //글 하나 조회 테스트
    @GetMapping(value = "/test/{boardId}")
    public BoardInfoMapping boardInfo(@AuthenticationPrincipal @PathVariable("boardId") Long boardId){
        return boardRepositoryTest.findByBoardId(boardId);
    }

    //글 수정 폼
//    @GetMapping(value = "/boards/edit")
//    public String updateBoardForm(@RequestBody @PathVariable("boardId") Long boardId, Model model) {
//        Board board = boardService.findOne(boardId);
//
//        BoardEditRequestDto form = new BoardEditRequestDto();
//        form.setBoardId(board.getId());
//        form.setContent(board.getContent());
//        form.setPrivacy(board.isPrivacy());
//
//        model.addAttribute("form", form);
//        return "boards/updateBoardForm";
//    }

    //글 수정
    @PutMapping(value = "/boards/edit")
    public Board updateBoard(@RequestBody BoardEditRequestDto dto){

        Board board = boardService.findOne(dto.getBoardId());

        System.out.println("id: " + dto.getBoardId());
        System.out.println("content: "+ dto.getContent());
        System.out.println("privacy: " + dto.isPrivacy());

        boardService.updateBoard(dto);

        return board;

        //  return boardService.findOne(dto.getBoardId());
        //return "redirect:/boards";
        //@ModelAttribute("form") @PathVariable("boardId")
    }


    //글 삭제 폼
    @GetMapping(value = "/boards/{boardId}/delete")
    public String deleteBoardForm(@PathVariable("boardId") Long boardId, Model model) {
        Board board = boardService.findOne(boardId);

        BoardRequestDto form = new BoardRequestDto();
        form.setBoardId(board.getId());

        model.addAttribute("form", form);
        return "boards/updateBoardForm";
    }


    //글 삭제
    @DeleteMapping(value = "/boards/{boardId}/delete")
    public String deleteBoard(@ModelAttribute("form") @RequestBody BoardRequestDto form){
        Board board = boardService.findOne(form.getBoardId());
        boardService.delete(board);
        //return board;
        return "redirect:/boards";
    }

}


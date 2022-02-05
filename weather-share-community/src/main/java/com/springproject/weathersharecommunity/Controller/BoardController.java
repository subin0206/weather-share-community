package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.BoardEditRequestDto;
import com.springproject.weathersharecommunity.Controller.dto.BoardRequestDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.domain.Member;
import com.springproject.weathersharecommunity.service.BoardService;
import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@ConfigurationProperties(prefix="server.upload")
public class BoardController {

    private final BoardService boardService;

//    @GetMapping("/boards/new")
//    public String createForm(Model model) {
//        model.addAttribute("BoardRequestDto", new BoardRequestDto());
//        return "board/createBoardForm";
//    }

    //글 등록
    @PostMapping("/boards/new")
    public Board create(@Valid @RequestBody BoardRequestDto boardRequestDto, BindingResult result) {

        Board board = new Board();

        if (result.hasErrors()) {
            return board;
            //return "board/createBoardForm";
        }
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) user.getPrincipal();

        board.setMember(member);
        board.setContent(boardRequestDto.getContent());
        board.setImages(boardRequestDto.getImages());
        board.setCreateDate(boardRequestDto.getCreateDate());
        board.setStatus(boardRequestDto.getStatus());
        board.setPrivacy(boardRequestDto.isPrivacy());

        boardService.create(board);

        return board;

    }

    //글 조회
    @GetMapping(value = "/boards")
    public List<Board> list(Model model) {
        List<Board> boards = boardService.findBoards();
        model.addAttribute("boards", boards);
        return boards;
        //return "boards/boardsList";
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


package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.Controller.dto.BoardRequestDto;
import com.springproject.weathersharecommunity.domain.Board;
import com.springproject.weathersharecommunity.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards/new")
    public String createForm(Model model) {
        model.addAttribute("BoardRequestDto", new BoardRequestDto());
        return "board/createBoardForm";
    }

    //글 등록
    @PostMapping("/boards/new")
    public String create(@Valid @RequestBody BoardRequestDto boardRequestDto, BindingResult result) {


        if (result.hasErrors()) {
            return "sorry";

            //return "board/createBoardForm";
        }

        Board board = new Board();

        board.setMember(boardRequestDto.getMember());
        board.setContent(boardRequestDto.getContent());
        board.setImages(boardRequestDto.getImages());
        board.setCreateDate(boardRequestDto.getCreateDate());
        board.setStatus(boardRequestDto.getStatus());
        board.setPrivacy(boardRequestDto.isPrivacy());

        boardService.create(board);

        return "redirect:/";

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
    @GetMapping(value = "/boards/{boardId}/edit")
    public String updateBoardForm(@PathVariable("boardId") Long boardId, Model model) {
        Board board = boardService.findOne(boardId);

        BoardRequestDto form = new BoardRequestDto();
        form.setBoardId(board.getId());
        form.setContent(board.getContent());
        form.setPrivacy(board.isPrivacy());

        model.addAttribute("form", form);
        return "boards/updateBoardForm";
    }

    //글 수정
    @PutMapping(value = "/boards/{boardId}/edit")
    public String updateBoard(@ModelAttribute("form") @RequestBody BoardRequestDto form){
        boardService.updateBoard(form.getBoardId(), form.getContent(), form.isPrivacy());
        return "redirect:/boards";
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
        return "redirect:/boards";
    }





}

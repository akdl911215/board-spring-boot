package com.jh.board.controller;

import com.jh.board.dto.BoardDTO;
import com.jh.board.dto.PageRequestDTO;
import com.jh.board.dto.PageResultDTO;
import com.jh.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public PageResultDTO list(PageRequestDTO pageRequestDTO) {

        log.info("list : " + pageRequestDTO);

        PageResultDTO response = boardService.getList(pageRequestDTO);
        log.info("response : " + response);

        return response;
    }

    @GetMapping("/register")
    public String register() {
        log.info("register get...");

        return "register post please";
    }

    @PostMapping("/register")
    public BoardDTO registerPost(BoardDTO dto) {
        log.info("register dto : " + dto);

        BoardDTO boardDTO = boardService.register(dto);
        log.info("boardDTO : " + boardDTO);

        return boardDTO;
    }

    @GetMapping("/read")
    public BoardDTO read(Long id) {
        log.info("read board id : " + id);

        BoardDTO boardDTO = boardService.get(id);
        log.info("boardDTO : " + boardDTO);

        return boardDTO;
    }

    @PatchMapping("/remove")
    public BoardDTO remove(long id) {
        log.info("remove board id : " + id);

        BoardDTO boardDTO = boardService.removeRelatedBoard(id);
        log.info("boardDTO : " + boardDTO);

        return boardDTO;
    }

    @PatchMapping("/modify")
    public BoardDTO modify(BoardDTO dto) {
        log.info("modify dto : " + dto);

        BoardDTO boardDTO = boardService.modify(dto);
        log.info("boardDTO : " + boardDTO);

        return boardDTO;
    }
}

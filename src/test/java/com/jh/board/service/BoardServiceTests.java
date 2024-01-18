package com.jh.board.service;

import com.jh.board.dto.BoardDTO;
import com.jh.board.dto.PageRequestDTO;
import com.jh.board.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testModify() {

        BoardDTO boardDTO = BoardDTO.builder()
                .id(2L)
                .title("title..1.")
                .content("content.1..")
                .build();

        BoardDTO response = boardService.modify(boardDTO);
        System.out.println(response);

    }

    @Test
    public void testRemove() {
        Long bno = 1L;

        boardService.removeRelatedBoard(bno);
    }

    @Test
    public void testGet() {

        Long id = 100L;

        BoardDTO boardDTO = boardService.get(id);

        System.out.println(boardDTO);
    }


    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for (BoardDTO boardDTO: result.getDtoList()) {
            System.out.println(boardDTO);
        }
    }

    @Test
    public void testRegister() {

        BoardDTO dto = BoardDTO.builder()
                .title("Test.")
                .content("Test...")
                .writerEmail("user100@naver.com") // 현재 데이터베이스에 존재하는 회원 이메일
                .build();

        BoardDTO response = boardService.register(dto);

    }
}

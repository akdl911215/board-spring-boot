package com.jh.board.service;

import com.jh.board.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

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

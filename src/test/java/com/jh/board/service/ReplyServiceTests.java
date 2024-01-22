package com.jh.board.service;

import com.jh.board.dto.ReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Test
    public void testGetList() {

        Long id = 100L;

        List<ReplyDTO> replyDTOList = replyService.getList(id);

        replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));
    }

}

package com.jh.board.repository;

import com.jh.board.dto.Board;
import com.jh.board.dto.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply() {

        IntStream.rangeClosed(1, 300).forEach(i -> {

            // 1부터 100까지의임의의 번호
            long bno = (long)(Math.random() * 100) + 1;

            Board board = Board.builder().id(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply......." + i)
                    .board(board)
                    .replyer("guest" + i)
                    .build();

            replyRepository.save(reply);
        });
    }
}

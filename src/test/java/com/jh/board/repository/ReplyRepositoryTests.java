package com.jh.board.repository;

import com.jh.board.entity.Board;
import com.jh.board.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testListByBoard() {

        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByIdDesc(Board.builder().id(1L).build());

        replyList.forEach(reply -> System.out.println(reply));
    }

    @Test
    public void readReply1() {

        Optional<Reply> result = replyRepository.findById(1L);

        Reply reply = result.get();

        System.out.println(reply);
        System.out.println(reply.getBoard());
    }

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

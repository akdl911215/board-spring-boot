package com.jh.board.repository;

import com.jh.board.entity.Board;
import com.jh.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // Board 삭지시에 댓글들 삭제를 위한 조회 용도
    @Query("SELECT r FROM Reply r WHERE r.board.id = :boardId")
    Optional<List<Reply>> getListFindByBoardId(Long boardId);

    // 게시물로 댓글 목록 가져오기
    List<Reply> getRepliesByBoardOrderByIdDesc(Board board);
}

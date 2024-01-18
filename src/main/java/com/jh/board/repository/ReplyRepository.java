package com.jh.board.repository;

import com.jh.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("SELECT r FROM Reply r WHERE r.board.id = :boardId")
    Optional<List<Reply>> getListFindByBoardId(Long boardId);
}

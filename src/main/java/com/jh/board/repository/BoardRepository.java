package com.jh.board.repository;

import com.jh.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 한개의 로우(Object) 내에 Object[ ] 로 나옴
    @Query("select b, w from Board b left join b.writer w where b.id = :id")
    Object getBoardWithWriter(@Param("id") Long id);

    @Query("select b, r from Board b left join Reply r on r.board = b where b.id = :id")
    List<Object[]> getBoardWithReply(@Param("id") Long id);

    @Query("SELECT b, w, count(r) " +
            " FROM Board b LEFT JOIN b.writer w " +
            " LEFT OUTER JOIN Reply r ON r.board = b" +
            " WHERE b.id = :id")
    Object getBoardByBoardId(@Param("id") Long id);

    @Query(value = "SELECT b, w, count(r) " +
            " FROM Board b" +
            " LEFT JOIN b.writer w " +
            " LEFT JOIN Reply r ON r.board = b" +
            " GROUP BY b",
            countQuery = "SELECT count(b) FROM Board b"
    )
    Page<Object[]> getBoardWithReplyCount(Pageable pageable); // 목록 화면에 필요한 데이터
}

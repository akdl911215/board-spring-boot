package com.jh.board.repository.search;

import com.jh.board.entity.Board;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchBoardRepository {

    List<Tuple> search1();

    Page<Object[]> searchPage(String type, String Keyword, Pageable pageable);
}

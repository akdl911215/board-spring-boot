package com.jh.board.service;

import com.jh.board.dto.BoardDTO;
import com.jh.board.entity.Board;
import com.jh.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public BoardDTO register(BoardDTO dto) {

        log.info("register dto : " + dto);

        Board board = dtoToEntity(dto);

        boardRepository.save(board);

        BoardDTO boardDTO = entityToDto(board);

        return boardDTO;
    }
}

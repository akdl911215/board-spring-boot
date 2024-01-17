package com.jh.board.service;

import com.jh.board.dto.BoardDTO;
import com.jh.board.entity.Board;
import com.jh.board.entity.Member;

public interface BoardService {

    BoardDTO register(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();

        Board board = Board.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default BoardDTO entityToDto(Board entity) {
        BoardDTO boardDTO = BoardDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();

        return boardDTO;
    }
}

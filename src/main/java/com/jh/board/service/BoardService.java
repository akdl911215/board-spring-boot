package com.jh.board.service;

import com.jh.board.dto.BoardDTO;
import com.jh.board.dto.PageRequestDTO;
import com.jh.board.dto.PageResultDTO;
import com.jh.board.entity.Board;
import com.jh.board.entity.Member;

import java.time.LocalDateTime;

public interface BoardService {

    BoardDTO register(BoardDTO dto);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO); // 목록 처리

    BoardDTO get(Long id);

    BoardDTO removeRelatedBoard(Long id);

    BoardDTO modify(BoardDTO boardDTO);

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

    default BoardDTO entityToDto(Board board) {
        BoardDTO boardDTO = BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .deletedAt(board.getDeletedAt())
                .build();

        return boardDTO;
    }

    default BoardDTO entityToDto2(Board board, Member member, Long replyCount) {
        BoardDTO boardDTO = BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .deletedAt(board.getDeletedAt())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue()) // long 으로 나오므로 int 로 처리하도록
                .build();

        return boardDTO;
    }
}

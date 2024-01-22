package com.jh.board.service;

import com.jh.board.dto.ReplyDTO;
import com.jh.board.entity.Board;
import com.jh.board.entity.Reply;

import java.util.List;

public interface ReplyService {

    // 댓글의 등록
    ReplyDTO register(ReplyDTO replyDTO);

    // 특정 게시물의 댓글 목록
    List<ReplyDTO> getList(Long id);

    // 댓글 수정
    ReplyDTO modify(ReplyDTO replyDTO);

    // 댓글 삭제
    ReplyDTO remove(Long id);

    // ReplyDTO 를 Reply 객체로 변환 Board 객체의 처리가 수반됨
    default Reply dtoToEntity(ReplyDTO replyDTO) {
        Board board = Board.builder()
                .id(replyDTO.getBoardId())
                .build();

        Reply reply = Reply.builder()
                .id(replyDTO.getId())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();

        return reply;
    }

    // Reply 객체를 ReplyDTO 로 변환 Board 객체가 필요하지 않으므로 게시물 번호만
    default ReplyDTO entityToDTO(Reply reply) {

        ReplyDTO replyDTO = ReplyDTO.builder()
                .id(reply.getId())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .deletedAt(reply.getDeletedAt())
                .build();

        return replyDTO;
    }
}

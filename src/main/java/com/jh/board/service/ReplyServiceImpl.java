package com.jh.board.service;

import com.jh.board.dto.ReplyDTO;
import com.jh.board.entity.Board;
import com.jh.board.entity.Reply;
import com.jh.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public ReplyDTO register(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);

        ReplyDTO convertEntity = entityToDTO(reply);

        return convertEntity;
    }

    @Override
    public List<ReplyDTO> getList(Long id) {

        List<Reply> result = replyRepository
                .getRepliesByBoardOrderByIdDesc(Board.builder().id(id).build());

        List<ReplyDTO> dtoList = result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public ReplyDTO modify(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        Reply entity = replyRepository.save(reply);

        ReplyDTO convertReply = entityToDTO(entity);

        return convertReply;
    }

    @Override
    public ReplyDTO remove(Long id) {

        Optional<Reply> result = replyRepository.findById(id);

        if (result.isPresent()) {
            Reply reply = result.get();

            reply.changeReplyDeletedAt(LocalDateTime.now());

            Reply entity = replyRepository.save(reply);
            ReplyDTO convertReply = entityToDTO(entity);

            return convertReply;
        }

        return null;
    }
}

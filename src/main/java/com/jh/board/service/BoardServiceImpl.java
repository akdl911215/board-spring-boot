package com.jh.board.service;

import com.jh.board.dto.BoardDTO;
import com.jh.board.dto.PageRequestDTO;
import com.jh.board.dto.PageResultDTO;
import com.jh.board.entity.Board;
import com.jh.board.entity.Member;
import com.jh.board.entity.Reply;
import com.jh.board.repository.BoardRepository;
import com.jh.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public BoardDTO register(BoardDTO dto) {

        log.info("register dto : " + dto);

        Board board = dtoToEntity(dto);

        boardRepository.save(board);

        BoardDTO boardDTO = entityToDto(board);

        return boardDTO;
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info("getLst dto : " + pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDto2((Board) en[0], (Member) en[1], (Long) en[2]));

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("id").descending()));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long id) {

        log.info("get start");

        Object result = boardRepository.getBoardByBoardId(id);

        Object[] arr = (Object[]) result;

        BoardDTO dto = entityToDto2((Board) arr[0], (Member) arr[1], (Long) arr[2]);

        return dto;
    }

    @Transactional
    @Override
    public BoardDTO removeRelatedBoard(Long id) {
        // 삭제 기능 구현, 트랜잭션 추가

        log.info("removeRelatedBoard start");

        Optional<Board> boardResult = boardRepository.findById(id);
        log.info("boardResult : " + boardResult);
        LocalDateTime dateTime = LocalDateTime.now();


        if (boardResult.isPresent()) {
            Board boardEntity = boardResult.get();

            Optional<List<Reply>> replyResult = replyRepository.getListFindByBoardId(boardResult.get().getId());
            log.info("replyResult.isPresent() : " + replyResult.isPresent());
            if (replyResult.isPresent()) {
                List<Reply> replyEntity = replyResult.get();

                replyEntity.forEach(el -> {
                    el.changeReplyDeletedAt(dateTime);
                    log.info("reply entity el : " + el);
                    replyRepository.save(el);
                });
            }

            boardEntity.changeBoardDeletedAt(dateTime);
            Board convertBoardEntity = boardRepository.save(boardEntity);

            BoardDTO response = entityToDto(convertBoardEntity);
            log.info("board dto response : " + response);

            return response;


        }


        return null;
    }

    @Override
    public BoardDTO modify(BoardDTO boardDTO) {

        log.info("board modify start");
        Optional<Board> result = boardRepository.findById(boardDTO.getId());
        log.info("result : " + result);

        if (result.isPresent()) {
            Board board = result.get();
            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());

            Board entity = boardRepository.save(board);
            log.info("entity : " + entity);
            BoardDTO response = entityToDto(entity);
            log.info("response : " + response);

            return response;
        }

        return null;
    }
}

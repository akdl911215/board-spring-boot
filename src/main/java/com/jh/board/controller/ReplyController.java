package com.jh.board.controller;

import com.jh.board.dto.ReplyDTO;
import com.jh.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 모든 메서드의 기본 리턴 타입은 기본적으로 JSON
@RequestMapping("/reply/")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping(value = "/board/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("id") Long id) {
        // ResponseEntity 객체를 이용하면 HTTP의 상태 코드 등을 같이 전달 가능
        // PathVariable으로 처리하면 {id} 같이 {} 내의 값을 처리할 수 있다.
        log.info("get list dto id : " + id);

        ResponseEntity response = new ResponseEntity<>(replyService.getList(id), HttpStatus.OK);
        log.info("response : " + response);

        return response;
    }

    @PostMapping("/register")
    public ResponseEntity<ReplyDTO> register(ReplyDTO replyDTO) {
        log.info("register replyDTO : " + replyDTO);

        ReplyDTO dto = replyService.register(replyDTO);

        ResponseEntity response = new ResponseEntity<>(dto, HttpStatus.OK);

        log.info("response : " + response);

        return response;
    }

    @PatchMapping("/remove")
    public ResponseEntity<ReplyDTO> remove(Long id) {
        log.info("remove id : " + id);

        ReplyDTO dto = replyService.remove(id);
        log.info("remove dto : " + dto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

package com.jh.board.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long id;

    private String title;

    private String content;

    private String writerEmail; // 작성자의 이메일(id)

    private String writerName; // 작성자의 이름

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private int replyCount; // 해당 게시글의 댓글 수
}

package com.jh.board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board") // @ToString 주의
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String replyer;

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    @ManyToOne
    private Board board; // 연관 관계 지정

    public void changeReplyDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }
}

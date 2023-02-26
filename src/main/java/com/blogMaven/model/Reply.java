package com.blogMaven.model;

import com.blogMaven.dto.ReplyRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Entity
    public class Reply {
        @Id
        @GeneratedValue
        private int id;

        @Column(nullable = false, length = 200)
        private String content;

        @ManyToOne // reply : many , board : one
        @JoinColumn(name = "boardId")
        private Board board;

        @ManyToOne
        @JoinColumn(name = "userId")
        private User user;

        @CreationTimestamp
        private Timestamp createDate;

        public void update(User user, Board board, String content){
            setUser(user);
            setBoard(board);
            setContent(content);
        }
    }


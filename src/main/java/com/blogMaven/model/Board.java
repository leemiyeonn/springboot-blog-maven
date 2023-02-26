package com.blogMaven.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false , length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인

    private int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER) // board = many , user = one
    @JoinColumn(name="userId")
    private User user; // db : object 저장 불가능 - fk 사용 / jpa : object 저장 가능 , user 객체 참조

    @OneToMany(mappedBy = "board" , fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"board"}) // 무한참조 방지 - Board 통해서 Reply 참조할때 reply.board 무시한다
    @OrderBy("id desc")
    // FetchType.EAGER 모든 데이터 불러온다 , FetchType.Lazy 필요할 때 데이터 불러온다
    //( mappedBy = "field" )- 연관관계의 주인 x (fk x) , db에 column 생성 x , Reply table 의 board = fk
    //          - select board 할 때 join reply
    private List<Reply> replies;

    @CreationTimestamp
    private Timestamp createDate;
}

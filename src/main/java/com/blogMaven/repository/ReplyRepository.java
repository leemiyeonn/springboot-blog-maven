package com.blogMaven.repository;

import com.blogMaven.dto.ReplyRequestDto;
import com.blogMaven.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

    @Modifying
    @Query(value =
            "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1,?2,?3,now())",
            nativeQuery = true)
    int replySave(int userId, int boardId, String content);
    // jdbc insert update delete 업데이트된 행의 개수 리턴

}

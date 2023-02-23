package com.blogMaven.service;

import com.blogMaven.config.auth.PrincipalDetail;
import com.blogMaven.model.Board;
import com.blogMaven.model.RoleType;
import com.blogMaven.model.User;
import com.blogMaven.repository.BoardRepository;
import com.blogMaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void write (Board board, User user) { // title , content

        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);

    }


    public Page<Board> boardList (Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
}

package com.blogMaven.service;

import com.blogMaven.config.auth.PrincipalDetail;
import com.blogMaven.dto.ReplyRequestDto;
import com.blogMaven.model.Board;
import com.blogMaven.model.Reply;
import com.blogMaven.model.RoleType;
import com.blogMaven.model.User;
import com.blogMaven.repository.BoardRepository;
import com.blogMaven.repository.ReplyRepository;
import com.blogMaven.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor // 초기화 필수인 필드(?)를 (final) 생성자 호출 할 때 파라미터에 넣어서 초기화
public class BoardService {

    private final BoardRepository boardRepository; // final 초기화 필수
    private final ReplyRepository replyRepository;

    @Transactional
    public void write (Board board, User user) { // title , content

        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);

    }

    @Transactional(readOnly = true)
    public Page<Board> boardList (Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board boardDetail (int id) {
        return boardRepository.findById(id)
                        .orElseThrow(() -> {
                        return new IllegalArgumentException(" 게시글 상세보기 실패 : 작성자를 찾을 수 없습니다 ");
                });
    }

    @Transactional
    public void boardDelete (int id) {

        boardRepository.deleteById(id);

    }

    @Transactional
    public void edit (int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException(" 게시글 수정 실패 : 게시글을 찾을 수 없습니다 ");
                }); // 영속화

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수 종료시 (service 종료) 트랜잭션 종료 , 이때 더티체킹 - 자동 업데이트 db flush (commit)
    }

    @Transactional
    public void writeReply(ReplyRequestDto replyRequestDto) {

        replyRepository.replySave(replyRequestDto.getUserId(), replyRequestDto.getBoardId(), replyRequestDto.getContent());

    }

    @Transactional
    public void replyDelete(int replyId) {

        replyRepository.deleteById(replyId);

    }
}

package com.blogMaven.controller.api;

import com.blogMaven.config.auth.PrincipalDetail;
import com.blogMaven.dto.ReplyRequestDto;
import com.blogMaven.dto.ResponseDto;
import com.blogMaven.model.Board;
import com.blogMaven.model.Reply;
import com.blogMaven.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save (@RequestBody Board board,
                                      @AuthenticationPrincipal PrincipalDetail principalDetail){

        boardService.write(board, principalDetail.getUser());

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById (@PathVariable int id) {

        boardService.boardDelete(id);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update (@PathVariable int id,
                                        @RequestBody Board board){

        boardService.edit(id, board);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }


    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave (@PathVariable int boardId,
                                           @RequestBody ReplyRequestDto reply){

        boardService.writeReply(reply);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

}

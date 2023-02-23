package com.blogMaven.controller;

import com.blogMaven.config.auth.PrincipalDetail;
import com.blogMaven.model.Board;
import com.blogMaven.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"","/"})
    public String index(Model model,
                        @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable ) {
        model.addAttribute("boards",boardService.boardList(pageable)); // request 정보
        return "index"; // viewResolver 작동
    }

    @GetMapping("/board/{id}")
    public String findByid(@PathVariable int id, Model model){

        model.addAttribute("board", boardService.boardDetail(id));
        return "board/detail";
    }
    // USER 권한 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }


    // 로그인 사용자만 접근 가능
//    @GetMapping({"","/"})
//    public String index(@AuthenticationPrincipal PrincipalDetail principal) {
//
//        System.out.println(" 로그인 사용자 ID : "+ principal.getUsername());
//        return "index";
//    }

}

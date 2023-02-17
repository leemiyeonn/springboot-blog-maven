package com.blogMaven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로 - /auth/**
// http://localhost:8080/ - index.jsp 허용
// resources/static - /js/** , /css/** , /image/**
@Controller
public class UserController {
    @GetMapping("/auth/joinForm")
    public String joinForm(){

        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){

        return "user/loginForm";
    }
}

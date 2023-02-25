package com.blogMaven.controller.api;

import com.blogMaven.config.auth.PrincipalDetail;
import com.blogMaven.dto.ResponseDto;
import com.blogMaven.model.User;
import com.blogMaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){

        System.out.println("UserApiController : save 호출");

        userService.signUp(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
        // 자바 Object를 JSON으로 변환해서 return (Jackson)
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update (@RequestBody User user) {


        userService.edit(user);
        // 트랜잭션 종료 - db 값 변경
        // 세션 값 변경 X

        // 세션 등록 - db 변경된 값으로 로그인
        // AuthenticationManager에 접근해서 로그인 - Authentication 객체 생성 - 자동으로 세션에 넣어줌
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);

    }

}

package com.blogMaven.controller.api;

import com.blogMaven.dto.ResponseDto;
import com.blogMaven.model.RoleType;
import com.blogMaven.model.User;
import com.blogMaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController : save 호출");
        user.setRole(RoleType.USER);
        userService.signUp(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
        // 자바 Object를 JSON으로 변환해서 반환 (Jackson)
    }

    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user){
        System.out.println("UserApiController : login 호출");
        User principal = userService.logIn(user); // principal : 접근 주체

        if(principal != null){
            session.setAttribute("principal",principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);

    }
}

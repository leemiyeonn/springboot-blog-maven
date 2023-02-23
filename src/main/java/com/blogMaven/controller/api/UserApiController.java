package com.blogMaven.controller.api;

import com.blogMaven.dto.ResponseDto;
import com.blogMaven.model.User;
import com.blogMaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){

        System.out.println("UserApiController : save 호출");

        userService.signUp(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
        // 자바 Object를 JSON으로 변환해서 return (Jackson)
    }

}

package com.blogMaven.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code){ // 데이터를 리턴해주는 컨트롤러 함수

        // POST 방식으로 key=value 데이터를 보내야함
        RestTemplate restTemplate = new RestTemplate();

        // HttpHeaders 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","530c1ec488446bf3ec713711a22969d4");
        params.add("redirect_uri","http://localhost:8080/auth/kakao/callback");
        params.add("code",code); // 변수로 지정해서 사용해야함

        // HttpHeaders + HttpBody 를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
                new HttpEntity<>(params,headers);

        // HTTP 요청하기 - POST 방식 , response 변수 응답받음
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        return response.getBody();
    }
}

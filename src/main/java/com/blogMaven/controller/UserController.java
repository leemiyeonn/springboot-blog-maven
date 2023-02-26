package com.blogMaven.controller;

import com.blogMaven.model.KakaoProfile;
import com.blogMaven.model.OAuthToken;
import com.blogMaven.model.User;
import com.blogMaven.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

// 인증이 안된 사용자들이 출입할 수 있는 경로 - /auth/**
// http://localhost:8080/ - index.jsp 허용
// resources/static - /js/** , /css/** , /image/**
@Controller
public class UserController {

    @Value("${cos.key}")
    private String cosKey;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/auth/joinForm")
    public String joinForm(){

        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){

        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String info(){
        return "/user/updateForm";
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) throws JsonProcessingException { // 데이터를 리턴해주는 컨트롤러 함수

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

        // Gson , Json Simple , ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;

        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        RestTemplate restTemplate2 = new RestTemplate();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

        // HTTP 요청하기 - POST 방식 , response 변수 응답받음
        ResponseEntity<String> response2 = restTemplate2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        ObjectMapper objectMapper2 = new ObjectMapper();
        // objectMapper2.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE); // 네이밍 전략 추가 (Snake -> Camel)
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        // User 오브젝트 : username, password, email
        System.out.println(" 카카오 아이디 : " + kakaoProfile.getId());
        System.out.println(" 카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
        System.out.println(" 카카오 엑세스 토큰 : " + oAuthToken.getAccess_token());

        System.out.println(" 블로그 서버 유저 네임 : " + kakaoProfile.getKakao_account().getEmail() +
                "_" + kakaoProfile.getId());
        System.out.println(" 블로그 서버 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        // 중복되지 않은 특정값을 만들어내는 알고리즘
        // UUID garbagePassword = UUID.randomUUID();

        System.out.println(" 블로그 서버 패스워드 : " + cosKey);

        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
                .email(kakaoProfile.getKakao_account().getEmail())
                .password(cosKey)
                .oauth("kakao")
                .build();


        User originUser = userService.findUser(kakaoUser.getUsername());
        // 가입하지 않은 회원
        if (originUser.getUsername() == null){
            System.out.println(" 신규 회원입니다. 회원가입을 진행합니다. ");
            userService.signUp(kakaoUser);
        }

        System.out.println(" 자동 로그인을 진행합니다. ");
        // 로그인 처리
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);



        return "redirect:/";
    }


}

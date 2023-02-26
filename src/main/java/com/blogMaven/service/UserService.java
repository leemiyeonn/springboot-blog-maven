package com.blogMaven.service;

import com.blogMaven.model.RoleType;
import com.blogMaven.model.User;
import com.blogMaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void signUp(User user) {

        String rawPassword = user.getPassword(); // 비밀번호 원문
        String encPassword = encoder.encode(rawPassword); // 비밀번호 해쉬

        user.setPassword(encPassword);
        user.setRole(RoleType.USER);

        userRepository.save(user);
    }

    @Transactional
    public void edit(User user) {

        User persistance = userRepository.findById(user.getId()).orElseThrow(
                ()->{return new IllegalArgumentException(" 회원 조회 실패 ");}
        ); // 영속화 -> 영속화된 user 를 수정

        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword); // 암호화한 password

        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());

    }

    // edit 함수 종료시 = userService 종료시 = 트랜잭션 종료 = commit
    // 영속화된 persistance 객체 변화 감지 - 더티체킹 - db에 update

    @Transactional(readOnly = true)
    public User findUser(String username) {

        User user = userRepository.findByUsername(username).orElseGet(
                () -> {return new User();});

        return user;
    }

}

package com.blogMaven.service;

import com.blogMaven.model.RoleType;
import com.blogMaven.model.User;
import com.blogMaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


}

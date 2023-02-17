package com.blogMaven.service;

import com.blogMaven.model.User;
import com.blogMaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void signUp(User user) {
        userRepository.save(user);
    }


    // Select 할 때 트랜잭션 시작 , 서비스 종료시에 트랜잭션 종료 ( 데이터 정합성 )
    @Transactional(readOnly = true)
    public User logIn(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

}

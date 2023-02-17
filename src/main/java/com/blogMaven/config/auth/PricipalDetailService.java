package com.blogMaven.config.auth;

import com.blogMaven.model.User;
import com.blogMaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PricipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username).orElseThrow(()->{
            return new UsernameNotFoundException(" 해당 사용자를 찾을 수 없습니다 : " + username);
                });
        return new PrincipalDetail(principal); // 시큐리티 세션에 유저 정보가 저장 ,

        // 이 오버라이드 구현해야 ,
        // 커스터마이징한 PrincipalDetail 에 user 객체 담아서 리턴할 수 있음
        // 기본 값 : UserDetails , 아이디 : user , 패스워드 : 콘솔
    }
}

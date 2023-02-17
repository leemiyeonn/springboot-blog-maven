package com.blogMaven.repository;

import com.blogMaven.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// 자동으로 bean 등록 , @Repository 어노테이션 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
    // SELECT * FROM user WHERE username = 1?
    Optional<User> findByUsername(String username);
}

// JPA Naming 쿼리
// SELECT * FROM user WHERE username = ?1 AND password = ?2;
// User findByUsernameAndPassword(String username, String password);

// 네이티브 쿼리
//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//    User login(String username, String password);

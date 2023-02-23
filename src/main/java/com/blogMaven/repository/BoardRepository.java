package com.blogMaven.repository;

import com.blogMaven.model.Board;
import com.blogMaven.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}


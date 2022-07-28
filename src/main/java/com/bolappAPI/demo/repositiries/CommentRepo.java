package com.bolappAPI.demo.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolappAPI.demo.models.Comment;

public interface CommentRepo  extends JpaRepository<Comment, Integer>{

}

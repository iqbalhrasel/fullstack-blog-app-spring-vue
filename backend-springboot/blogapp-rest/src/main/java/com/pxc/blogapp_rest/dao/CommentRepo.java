package com.pxc.blogapp_rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pxc.blogapp_rest.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}

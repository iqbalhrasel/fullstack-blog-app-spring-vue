package com.pxc.blogapp_rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pxc.blogapp_rest.model.count.CommentCount;

public interface CommentCountRepo extends JpaRepository<CommentCount, Integer>{
	CommentCount findByBlogId(int blogId);

}

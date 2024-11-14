package com.pxc.blogapp_rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pxc.blogapp_rest.model.count.LikeCount;

public interface LikeCountRepo extends JpaRepository<LikeCount, Integer> {
	LikeCount findByBlogId(int blogId);

}

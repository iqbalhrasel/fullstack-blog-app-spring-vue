package com.pxc.blogapp_rest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pxc.blogapp_rest.model.Likes;

public interface LikesRepo extends JpaRepository<Likes, Integer>{
	void deleteByAccountId(int accountId);
	Optional<Likes> findByAccountIdAndBlogId(int accountId, int blogId);

}

package com.pxc.blogapp_rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pxc.blogapp_rest.model.Blog;

public interface BlogRepo extends JpaRepository<Blog, Integer>{
	
	List<Blog> findAllByOrderByIdDesc();
	List<Blog> findTop4ByOrderByIdDesc();
	List<Blog> findTop3ByOrderByLikeCountCountDescPublishDateDesc();
	List<Blog> findByAccountIdOrderByIdDesc(int accountId);

}

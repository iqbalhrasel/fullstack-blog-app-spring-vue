package com.pxc.blogapp_rest.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pxc.blogapp_rest.dao.BlogRepo;
import com.pxc.blogapp_rest.model.Blog;
import com.pxc.blogapp_rest.model.count.CommentCount;
import com.pxc.blogapp_rest.model.count.LikeCount;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepo blogRepo;

	public List<Blog> getAllBlogs() {
		return blogRepo.findAllByOrderByIdDesc();
	}

	public Blog getOneBlogById(int blogId) {
		return blogRepo.findById(blogId).orElse(null);
	}

	public Blog saveOrUpdateBlog(Blog blog) {
		if(blog.getId() > 0) {
			Blog blogById = blogRepo.findById(blog.getId()).orElse(null);
			
			if(blogById == null) {
				return null;
			}
			else {
				blogById.setTitle(blog.getTitle());
				blogById.setBlogUrl(blog.getBlogUrl());
				blogById.setImgUrl(blog.getImgUrl());
				blogById.setContent(blog.getContent());
				blogById.setUpdateDate(LocalDate.now());
				return blogRepo.save(blogById);
			}
		}
		
		if(blog.getId() <1) {
			blog.setPublishDate(LocalDate.now());
			
			LikeCount likeCount = new LikeCount(0, blog, 0);
			blog.setLikeCount(likeCount);
			
			CommentCount commentCount = new CommentCount(0, blog, 0);
			blog.setCommentCount(commentCount);
		}
		
		return blogRepo.save(blog);
	}

	public List<Blog> getLatestBlogs() {
		return blogRepo.findTop4ByOrderByIdDesc();
	}

	public List<Blog> getPopularBlogs() {
		return blogRepo.findTop3ByOrderByLikeCountCountDescPublishDateDesc();
	}

	public List<Blog> findBlogsByUser(int accountId) {
		return blogRepo.findByAccountIdOrderByIdDesc(accountId);
	}

	public int deleteBlogById(int id) {
		Optional<Blog> blog = blogRepo.findById(id);
		if(blog.isPresent()) {
			blogRepo.deleteById(id);
			return 1;
		}
		return 0;
	}

}

package com.pxc.blogapp_rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pxc.blogapp_rest.model.Blog;
import com.pxc.blogapp_rest.service.BlogService;

@RestController
public class BlogController {
	
	private BlogService blogService;
	
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@GetMapping("test")
	public ResponseEntity<String> testing(){
		return new ResponseEntity<>("test success", HttpStatus.OK);
	}
	
	@GetMapping("blogs")
	public ResponseEntity<List<Blog>> allBlogs(){
		List<Blog> findBlogs = blogService.getAllBlogs();
		return new ResponseEntity<>(findBlogs, HttpStatus.OK);
	}
	
	@GetMapping("latestblogs")
	public ResponseEntity<List<Blog>> allLatestBlogs(){
		List<Blog> findBlogs = blogService.getLatestBlogs();
		return new ResponseEntity<>(findBlogs, HttpStatus.OK);
	}
	
	@GetMapping("popularblogs")
	public ResponseEntity<List<Blog>> allPopularBlogs(){
		List<Blog> findBlogs = blogService.getPopularBlogs();
		return new ResponseEntity<>(findBlogs, HttpStatus.OK);
	}
	
	@GetMapping("blog/{blogId}")
	public ResponseEntity<Blog> getOneBlog(@PathVariable int blogId){
		Blog findBlog = blogService.getOneBlogById(blogId);
		if(findBlog == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(findBlog, HttpStatus.OK);
	}
	
	@GetMapping("blogsbyuser/{accountId}")
	public ResponseEntity<List<Blog>> blogsByUser(@PathVariable int accountId){
		List<Blog> getBlogsByUser = blogService.findBlogsByUser(accountId);		
		return new ResponseEntity<>(getBlogsByUser,HttpStatus.OK);
	}
	
	@PostMapping("blog")
	public ResponseEntity<Blog> createNewBlog(@RequestBody Blog blog){
		Blog saveBlog = blogService.saveOrUpdateBlog(blog);
		if(saveBlog == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(saveBlog, HttpStatus.OK);
	}
	
	@PutMapping("blog")
	public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog){
		Blog saveBlog = blogService.saveOrUpdateBlog(blog);
		if(saveBlog == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(saveBlog, HttpStatus.OK);
	}
	
	@DeleteMapping("blog/{id}")
	public ResponseEntity<Integer> deleteBlog(@PathVariable int id){
		int deleteResponse = blogService.deleteBlogById(id);
		if(deleteResponse == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
	}

}

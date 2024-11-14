package com.pxc.blogapp_rest.model.count;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pxc.blogapp_rest.model.Blog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class CommentCount {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    private int id;

    @OneToOne
    @JoinColumn(name = "blog_id")
    @JsonIgnore
    private Blog blog;

    private int count;

	public CommentCount() {
	}

	public CommentCount(int id, Blog blog, int count) {
		this.id = id;
		this.blog = blog;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CommentCount [id=" + id + ", blog=" + blog + ", count=" + count + "]";
	}
    
    

}

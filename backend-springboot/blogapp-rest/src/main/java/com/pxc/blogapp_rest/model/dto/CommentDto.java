package com.pxc.blogapp_rest.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pxc.blogapp_rest.model.Account;
import com.pxc.blogapp_rest.model.Blog;

public class CommentDto {
	private int id;
	private Blog blog;	
	private String description;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate commentDate;
	
	private Account account;

	public CommentDto() {
	}

	public CommentDto(int id, Blog blog, String description, LocalDate commentDate, Account account) {
		this.id = id;
		this.blog = blog;
		this.description = description;
		this.commentDate = commentDate;
		this.account = account;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDate commentDate) {
		this.commentDate = commentDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", blog=" + blog + ", description=" + description + ", commentDate="
				+ commentDate + ", account=" + account + "]";
	}
	
	

}

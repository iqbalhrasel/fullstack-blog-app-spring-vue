package com.pxc.blogapp_rest.model.dto;

import com.pxc.blogapp_rest.model.Account;
import com.pxc.blogapp_rest.model.Blog;

public class LikeDto {
	private int id;
	private Blog blog;
	private Account account;
	
	public LikeDto() {
	}

	public LikeDto(int id, Blog blog, Account account) {
		this.id = id;
		this.blog = blog;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "LikeDto [id=" + id + ", blog=" + blog + ", account=" + account + "]";
	}
	
	

}

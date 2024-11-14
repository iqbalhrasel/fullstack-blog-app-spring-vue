package com.pxc.blogapp_rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pxc.blogapp_rest.model.dto.LikeDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "blog_id", referencedColumnName = "id")
	@JsonIgnore
	private Blog blog;
	
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;

	public Likes() {
	}

	public Likes(int id, Blog blog, Account account) {
		this.id = id;
		this.blog = blog;
		this.account = account;
	}
	
	public Likes(LikeDto likeDto) {
		this.id = likeDto.getId();
		this.blog = likeDto.getBlog();
		this.account = likeDto.getAccount();
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
		return "Likes [id=" + id + ", blog=" + blog + ", account=" + account + "]";
	}

	

}

package com.pxc.blogapp_rest.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pxc.blogapp_rest.model.dto.CommentDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "blog_id", referencedColumnName = "id")
	@JsonIgnore
	private Blog blog;
	
	private String description;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate commentDate;
	
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;

	public Comment() {
	}

	public Comment(int id, Blog blog, String description, LocalDate commentDate, Account account) {
		this.id = id;
		this.blog = blog;
		this.description = description;
		this.commentDate = commentDate;
		this.account = account;
	}
	
	public Comment(CommentDto commentDto) {
		this.id = commentDto.getId();
		this.blog = commentDto.getBlog();
		this.description = commentDto.getDescription();
		this.commentDate = commentDto.getCommentDate();
		this.account = commentDto.getAccount();
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
		return "Comment [id=" + id + ", blog=" + blog + ", description=" + description + ", commentDate=" + commentDate
				+ ", account=" + account + "]";
	}

	
}

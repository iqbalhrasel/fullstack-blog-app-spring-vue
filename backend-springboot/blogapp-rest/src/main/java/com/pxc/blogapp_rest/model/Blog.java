package com.pxc.blogapp_rest.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pxc.blogapp_rest.model.count.CommentCount;
import com.pxc.blogapp_rest.model.count.LikeCount;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;	
	
    @Column(columnDefinition = "TEXT")
	private String content;
	
	private String blogUrl;
	private String imgUrl;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate publishDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate updateDate;
	
	@OneToOne(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private LikeCount likeCount;
	
	@OneToOne(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private CommentCount commentCount;
	
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Likes> likes;
	
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;
	
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
	
	public Blog() {
	}

	public Blog(int id, String title, String content, String blogUrl, String imgUrl, LocalDate publishDate,
			LocalDate updateDate, LikeCount likeCount, CommentCount commentCount, List<Likes> likes,
			List<Comment> comments, Account account) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.blogUrl = blogUrl;
		this.imgUrl = imgUrl;
		this.publishDate = publishDate;
		this.updateDate = updateDate;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.likes = likes;
		this.comments = comments;
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBlogUrl() {
		return blogUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public LikeCount getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(LikeCount likeCount) {
		this.likeCount = likeCount;
	}

	public CommentCount getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(CommentCount commentCount) {
		this.commentCount = commentCount;
	}

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", blogUrl=" + blogUrl + ", imgUrl="
				+ imgUrl + ", publishDate=" + publishDate + ", updateDate=" + updateDate + ", likeCount=" + likeCount
				+ ", commentCount=" + commentCount + ", likes=" + likes + ", comments=" + comments + ", account="
				+ account + "]";
	}

	
}

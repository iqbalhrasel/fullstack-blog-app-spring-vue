package com.pxc.blogapp_rest.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pxc.blogapp_rest.dao.CommentCountRepo;
import com.pxc.blogapp_rest.dao.CommentRepo;
import com.pxc.blogapp_rest.dao.LikeCountRepo;
import com.pxc.blogapp_rest.dao.LikesRepo;
import com.pxc.blogapp_rest.model.Comment;
import com.pxc.blogapp_rest.model.Likes;
import com.pxc.blogapp_rest.model.count.CommentCount;
import com.pxc.blogapp_rest.model.count.LikeCount;
import com.pxc.blogapp_rest.model.dto.CommentDto;
import com.pxc.blogapp_rest.model.dto.LikeDto;

@Service
public class LikeCommentService {
	
	@Autowired
	private LikesRepo likesRepo;
	
	@Autowired
	private LikeCountRepo likeCountRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private CommentCountRepo commentCountRepo;
	
	private final Object lock = new Object();
	private final Object lock2 = new Object();

	@Transactional
	public int handleLikes(LikeDto likeDto) {
		synchronized (lock) {
			Likes likes = new Likes(likeDto);
			
			Optional<Likes> getExisitngLike = likesRepo.findByAccountIdAndBlogId(likes.getAccount().getId(), likes.getBlog().getId());
			LikeCount getLikeCount = likeCountRepo.findByBlogId(likes.getBlog().getId());
			
			if(getExisitngLike.isPresent()) {
				likesRepo.delete(getExisitngLike.get());
				
				getLikeCount.setCount(getLikeCount.getCount()-1);
				likeCountRepo.save(getLikeCount);
				return -1;
			}
			else {
				likesRepo.save(likes);
				
				getLikeCount.setCount(getLikeCount.getCount()+1);
				likeCountRepo.save(getLikeCount);
				return 1;
			}
		}
	}

	@Transactional
	public Comment addComment(CommentDto commentDto) {
		synchronized (lock2) {
			Comment comment = new Comment(commentDto);
			comment.setCommentDate(LocalDate.now());
			Comment savedComment = commentRepo.save(comment);
			
			CommentCount commentCount = commentCountRepo.findByBlogId(comment.getBlog().getId());
			commentCount.setCount(commentCount.getCount() + 1);
			commentCountRepo.save(commentCount);
			return savedComment;
		}
	}

	public Comment updateComment(CommentDto commentDto) {
		Comment getComment = commentRepo.findById(commentDto.getId()).orElse(null);
		if(getComment == null) {
			return null;
		}
		getComment.setCommentDate(LocalDate.now());
		getComment.setDescription(commentDto.getDescription());
		return commentRepo.save(getComment);
	}

	public int deleteComment(CommentDto commentDto) {
		Comment getComment = commentRepo.findById(commentDto.getId()).orElse(null);
		CommentCount getCommentCount = commentCountRepo.findByBlogId(commentDto.getBlog().getId());
		if(getComment == null) {
			return -1;
		}
		commentRepo.deleteById(commentDto.getId());
		
		getCommentCount.setCount(getCommentCount.getCount()-1);
		commentCountRepo.save(getCommentCount);
		return 1;
	}

}

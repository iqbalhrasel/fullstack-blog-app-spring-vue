package com.pxc.blogapp_rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pxc.blogapp_rest.model.Comment;
import com.pxc.blogapp_rest.model.dto.CommentDto;
import com.pxc.blogapp_rest.model.dto.LikeDto;
import com.pxc.blogapp_rest.service.LikeCommentService;

@RestController
public class LikeCommentController {
	
	private LikeCommentService likeCommentService;
	
	public LikeCommentController(LikeCommentService likeCommentService) {
		this.likeCommentService = likeCommentService;
	}	
	
	@PostMapping("handlelike")
	public ResponseEntity<Integer> handleLike(@RequestBody LikeDto likeDto){
		int likeResponse = likeCommentService.handleLikes(likeDto);
		return new ResponseEntity<>(likeResponse,HttpStatus.OK);
	}
	
	@PostMapping("comment")
	public ResponseEntity<Comment> addComment(@RequestBody CommentDto commentDto){
		Comment comment = likeCommentService.addComment(commentDto);
		return new ResponseEntity<>(comment,HttpStatus.OK);
	}
	
	@PutMapping("comment")
	public ResponseEntity<Comment> updateComment(@RequestBody CommentDto commentDto){
		Comment updateResponse = likeCommentService.updateComment(commentDto);
		if(updateResponse == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(updateResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("comment")
	public ResponseEntity<Integer> deleteComment(@RequestBody CommentDto commentDto){
		
		int deleteResponse = likeCommentService.deleteComment(commentDto);
		if(deleteResponse < 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

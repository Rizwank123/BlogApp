package com.masai.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.school.payload.ApiResponse;
import com.masai.school.payload.CommentDto;
import com.masai.school.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentServie;
	
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable Integer postId){
		CommentDto cmt=commentServie.createComment(comment, postId);
		
		return new ResponseEntity<CommentDto>(cmt,HttpStatus.CREATED);
	}
	 @DeleteMapping("/comments")
	 public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		 commentServie.deleteComment(commentId);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully",true),HttpStatus.OK);
	 }
	

}

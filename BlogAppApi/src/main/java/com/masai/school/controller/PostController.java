package com.masai.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.school.payload.PostDto;
import com.masai.school.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto createdPost=postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
		
	}
	//find by users 
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto> >getPostByUser(@PathVariable Integer userId){
		List<PostDto> posts=postService.getPostByUserId(userId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	//find by category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto> >getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts=postService.getPostByCategory(categoryId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("posts")
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto>posts=postService.getAllPost();
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
				
	}
	@GetMapping("posts/{postId}")
	public ResponseEntity<PostDto> findPostById(@PathVariable Integer postId){
		PostDto post=postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	

}

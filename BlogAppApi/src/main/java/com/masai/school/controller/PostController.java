package com.masai.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.masai.school.cnfig.AppConstant;
import com.masai.school.payload.ApiResponse;
import com.masai.school.payload.PostDto;
import com.masai.school.payload.PostResponse;
import com.masai.school.service.FileService;
import com.masai.school.service.PostService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@Valid @RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto createdPost=postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
		
	}
	//find by users 
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse >getPostByUser(@PathVariable Integer userId,
			@RequestParam(value="pageNumber", defaultValue = AppConstant.PAGE_NUMBER,required  = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize
			
			){
		PostResponse posts=postService.getPostByUserId(userId ,pageNumber,pageSize);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	//find by category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse >getPostByCategory(@PathVariable Integer categoryId,
			@RequestParam(value="pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize
			){
		PostResponse posts=postService.getPostByCategory(categoryId, pageNumber,pageSize);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir
			){
		PostResponse posts=postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		
		
		return new ResponseEntity<>(posts,HttpStatus.OK);
				
	}
	@GetMapping("posts/{postId}")
	public ResponseEntity<PostDto> findPostById(@PathVariable Integer postId){
		PostDto post=postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDto>UpdatePost(@Valid @PathVariable Integer postId,@RequestBody PostDto post){
		PostDto pst=postService.updatePost(post, postId);
		return new ResponseEntity<>(pst,HttpStatus.OK);
	}
	
	@DeleteMapping("posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted Successfully",true),HttpStatus.OK);
		
	}
	
	@GetMapping("posts/search/{keywords}")
	public ResponseEntity<List<PostDto>>SearchPost(@PathVariable String keywords){
		
		List<PostDto> post=postService.searchPost(keywords);
		
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	
	// image upload end-Point
	@PostMapping("post/image/upload/{postId}")
	public ResponseEntity<ImageResponse>uploadPostImage(@RequestParam ("image")MultipartFile image,@PathVariable Integer postId){
		
		String name=fileService.uploadImage(path, image);
		
		PostDto postDto=postService.getPostById(postId);
		postDto.setImageName(name);
		PostDto updatePostDto =postService.updatePost(postDto, postId);
		return new ResponseEntity<T>
		
	}

}

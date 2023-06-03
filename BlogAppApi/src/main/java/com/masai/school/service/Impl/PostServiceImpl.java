package com.masai.school.service.Impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.school.Repositroy.CategoryRepo;
import com.masai.school.Repositroy.PostRepo;
import com.masai.school.Repositroy.UserRepo;
import com.masai.school.entities.Category;
import com.masai.school.entities.Post;
import com.masai.school.entities.User;
import com.masai.school.exception.ResourceNotFoundException;
import com.masai.school.payload.PostDto;
import com.masai.school.service.PostService;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	ModelMapper modelmapper;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private UserRepo userRepo;
	@Override
	public PostDto createPost(PostDto post,Integer categoryId,Integer userId) {
		// TODO Auto-generated method stub
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		Post post1=modelmapper.map(post, Post.class);
		post1.setImageName("default.png");
		post1.setAddedDate(new Date());
		post1.setCategory(category);
		post1.setUser(user);
		Post newPost=postRepo.save(post1);
		return modelmapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto post, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post>posts=postRepo.findAll();
		List<PostDto> list = posts.stream()
			    .map((p) -> modelmapper.map(p, PostDto.class))
			    .collect(Collectors.toList());
		
		return list;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		return modelmapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> posts=postRepo.findByCategory(cat);
		List<PostDto> list = posts.stream()
			    .map((p) -> modelmapper.map(p, PostDto.class))
			    .collect(Collectors.toList());
		return list;
	}

	@Override
	public List<PostDto> getPostByUserId(Integer userId) {
		
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		List<Post> posts=postRepo.findByUser(user);
		List<PostDto> list = posts.stream()
			    .map((p) -> modelmapper.map(p, PostDto.class))
			    .collect(Collectors.toList());
		return list;
		
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}

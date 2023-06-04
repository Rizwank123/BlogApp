package com.masai.school.service.Impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.masai.school.Repositroy.CategoryRepo;
import com.masai.school.Repositroy.PostRepo;
import com.masai.school.Repositroy.UserRepo;
import com.masai.school.entities.Category;
import com.masai.school.entities.Post;
import com.masai.school.entities.User;
import com.masai.school.exception.ResourceNotFoundException;
import com.masai.school.payload.PostDto;
import com.masai.school.payload.PostResponse;
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
		Post pst=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		
		pst.setContent(post.getContent());
		pst.setImageName(post.getImageName());
		pst.setTitle(pst.getTitle());
		Post updatePost=postRepo.save(pst);
		return modelmapper.map(updatePost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable page = PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post>pagePost=postRepo.findAll(page);
		
		List<Post>posts=pagePost.getContent();
		List<PostDto> list = posts.stream()
			    .map((p) -> modelmapper.map(p, PostDto.class))
			    .collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(list);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalpages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		return modelmapper.map(post,PostDto.class);
	}

	@Override
	public PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize) {
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		Pageable page= PageRequest.of( pageNumber, pageSize);
		Page<Post> pagePost=postRepo.findByCategory(cat,page);
		List<Post>posts=pagePost.getContent();
		List<PostDto> list = posts.stream()
				.map((p) -> modelmapper.map(p, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(list);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalpages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}

	@Override
	public PostResponse getPostByUserId(Integer userId,Integer pageNumber,Integer pageSize) {
		
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		Pageable page= PageRequest.of( pageNumber, pageSize);
		Page<Post> pagePost=postRepo.findByUser(user,page);
		List<Post>posts=pagePost.getContent();
		List<PostDto> list = posts.stream()
			    .map((p) -> modelmapper.map(p, PostDto.class))
			    .collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(list);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalpages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
		
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
	List<Post>post=	postRepo.searchByTitle("%"+keyword+"%");
	List<PostDto>postDto=post.stream().map((p)->modelmapper.map(p, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
	}

}

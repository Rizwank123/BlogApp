package com.masai.school.service;

import java.util.List;

import com.masai.school.entities.Post;
import com.masai.school.payload.PostDto;

public interface PostService {
	PostDto createPost(PostDto post,Integer userId,Integer categoryId);
	PostDto updatePost(PostDto post,Integer postId);
	void deletePost(Integer postId);
	List<PostDto> getAllPost();
	PostDto getPostById(Integer postId);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUserId(Integer userId);
	List<PostDto> searchPost(String  keyword);

}

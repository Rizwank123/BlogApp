package com.masai.school.service;

import java.util.List;

import com.masai.school.entities.Post;
import com.masai.school.payload.PostDto;
import com.masai.school.payload.PostResponse;

public interface PostService {
	PostDto createPost(PostDto post,Integer userId,Integer categoryId);
	PostDto updatePost(PostDto post,Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPost(Integer pageNumber,Integer pageSize);
	PostDto getPostById(Integer postId);
	PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize);
	PostResponse getPostByUserId(Integer userId,Integer pageNumber,Integer pageSize);
	List<PostDto> searchPost(String  keyword);

}

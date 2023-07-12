package com.masai.school.service;

import java.util.List;

import com.masai.school.payload.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
	void deleteComment(Integer commentId);
	List<CommentDto> AllComments();

}

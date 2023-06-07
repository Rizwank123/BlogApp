package com.masai.school.service;

import com.masai.school.payload.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto,Integer postId);
	void deleteComment(Integer commentId);

}

package com.masai.school.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.school.Repositroy.CommentRepo;
import com.masai.school.Repositroy.PostRepo;
import com.masai.school.entities.Comment;
import com.masai.school.entities.Post;
import com.masai.school.exception.ResourceNotFoundException;
import com.masai.school.payload.CommentDto;
import com.masai.school.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRep;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		
		
		Comment cmnt=modelMapper.map(commentDto, Comment.class);
		cmnt.setPost(post);
		
		Comment createdComment=commentRep.save(cmnt);
		return modelMapper.map(createdComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment cmnt=commentRep.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment Id", commentId));
		commentRep.delete(cmnt);

	}
}

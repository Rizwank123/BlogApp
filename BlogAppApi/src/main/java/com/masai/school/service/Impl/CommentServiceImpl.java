package com.masai.school.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.school.Repositroy.CommentRepo;
import com.masai.school.Repositroy.PostRepo;
import com.masai.school.Repositroy.UserRepo;
import com.masai.school.entities.Comment;
import com.masai.school.entities.Post;
import com.masai.school.entities.User;
import com.masai.school.exception.ResourceNotFoundException;
import com.masai.school.payload.CommentDto;
import com.masai.school.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CommentRepo commentRep;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		List<User>list=new ArrayList<>();
		list.add(user);
		Comment cmnt=modelMapper.map(commentDto, Comment.class);
		cmnt.setPost(post);
		cmnt.setUsers(list);
		Comment createdComment=commentRep.save(cmnt);
		return modelMapper.map(createdComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment cmnt=commentRep.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment Id", commentId));
		commentRep.delete(cmnt);

	}

	@Override
	public List<CommentDto> AllComments() {
		
		List<Comment>list=commentRep.findAll();
		List<CommentDto> commentDto=list.stream().map((e)->modelMapper.map(e,CommentDto.class)).collect(Collectors.toList());
		return commentDto;
		
		
	}
}

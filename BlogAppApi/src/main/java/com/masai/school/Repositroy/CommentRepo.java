package com.masai.school.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.school.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}

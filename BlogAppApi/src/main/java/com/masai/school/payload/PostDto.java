package com.masai.school.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.masai.school.entities.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	private Set<Comment> comments=new HashSet<>();

}

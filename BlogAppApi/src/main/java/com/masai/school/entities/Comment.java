package com.masai.school.entities;

import jakarta.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comments")
@Getter
@Setter
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	 private String content;
	 
	 @ManyToOne
	
	 private Post post;
	
	 	@ManyToMany(mappedBy="comments")
	 	 private List<User> users=new ArrayList<>();

}

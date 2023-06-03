package com.masai.school.Repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.school.entities.Category;
import com.masai.school.entities.Post;
import com.masai.school.entities.User;

public interface PostRepo  extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	

}

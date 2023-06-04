package com.masai.school.Repositroy;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.school.entities.Category;
import com.masai.school.entities.Post;
import com.masai.school.entities.User;

public interface PostRepo  extends JpaRepository<Post, Integer>{
	Page<Post> findByUser(User user,Pageable page);
	Page<Post> findByCategory(Category category,Pageable page);
	

}

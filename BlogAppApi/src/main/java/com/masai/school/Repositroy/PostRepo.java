package com.masai.school.Repositroy;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.school.entities.Category;
import com.masai.school.entities.Post;
import com.masai.school.entities.User;

public interface PostRepo  extends JpaRepository<Post, Integer>{
	Page<Post> findByUser(User user,Pageable page);
	Page<Post> findByCategory(Category category,Pageable page);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String  title);
	

}

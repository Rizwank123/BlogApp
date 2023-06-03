package com.masai.school.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.school.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

package com.masai.school.service;

import java.util.List;

import com.masai.school.payload.CategoryDto;

public interface CategoryService {
public CategoryDto CreateCategory(CategoryDto categoryDto);
	
    public CategoryDto UpdateCategory(CategoryDto categoryDto,Integer categoryId);
    public void deleteCategory(Integer categoryId);
    public CategoryDto getCategoryById(Integer categoryId);
    public List<CategoryDto> getAllCategory();
    

}

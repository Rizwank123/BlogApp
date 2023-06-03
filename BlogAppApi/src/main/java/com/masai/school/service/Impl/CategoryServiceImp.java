package com.masai.school.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.masai.school.Repositroy.CategoryRepo;
import com.masai.school.entities.Category;
import com.masai.school.exception.ResourceNotFoundException;
import com.masai.school.payload.CategoryDto;
import com.masai.school.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto CreateCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category cat=modelMapper.map(categoryDto, Category.class);
		
		Category add=categoryRepo.save(cat);
		
		
		return  modelMapper.map(add, CategoryDto.class);
	}

	@Override
	public CategoryDto UpdateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatecategory=categoryRepo.save(cat);
		
		
		return modelMapper.map(updatecategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		categoryRepo.delete(cat);
		
		
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		
		return modelMapper.map(cat,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> list=categoryRepo.findAll();
		List<CategoryDto> cat = list.stream()
			    .map(e -> modelMapper.map(e, CategoryDto.class))
			    .collect(Collectors.toList());
		return cat;
	}

}

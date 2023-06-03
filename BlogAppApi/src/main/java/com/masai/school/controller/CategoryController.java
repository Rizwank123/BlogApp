package com.masai.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.school.entities.Category;
import com.masai.school.payload.ApiResponse;
import com.masai.school.payload.CategoryDto;
import com.masai.school.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping ("/")
	public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto  categorydto){
		CategoryDto createdCat=categoryService.CreateCategory(categorydto);
		return new ResponseEntity<>(createdCat,HttpStatus.CREATED);
		
	}
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto  categorydto,@PathVariable Integer categoryId){
		CategoryDto createdCat=categoryService.UpdateCategory(categorydto,categoryId);
		return new ResponseEntity<>(createdCat,HttpStatus.CREATED);
		
	}
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> findById(@PathVariable Integer categoryId){
		CategoryDto cat=categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.OK);
		
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto>categories=categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
	}
	@DeleteMapping("/{catgoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
	}
}
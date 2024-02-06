package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.payloads.CategoryDto;

public interface CategoryService {
	//create 
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updatecategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	
	void deleteCategory (Integer categoryId);
	
	//get
	
	CategoryDto getCategory(Integer categoryId);
	
	//getAll
	
	List<CategoryDto> getCategories();

}

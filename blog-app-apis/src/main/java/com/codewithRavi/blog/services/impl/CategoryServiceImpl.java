package com.codewithdurgesh.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.Entities.Category;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.repositories.CategoryRepo;
import com.codewithdurgesh.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelmapper;
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category cat=this.modelmapper.map(categoryDto, Category.class);
	Category addCat=	this.categoryRepo.save(cat);
		
		
		return this.modelmapper.map(addCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updatecategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		
	Category cat =	this.categoryRepo.findById(categoryId)
		.orElseThrow(()->new ResourceNotFoundException("Category", "Categor ID", categoryId));
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
	Category updatecat=	this.categoryRepo.save(cat);
		return this.modelmapper.map(updatecat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "Categor ID", categoryId));
		this.categoryRepo.delete(cat);
		
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category cat=this.categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category", "Categor ID", categoryId));
		
		return this.modelmapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		// TODO Auto-generated method stub
		List<Category>categories=this.categoryRepo.findAll();
	List <CategoryDto>catDto= 	categories.stream().map((cat)-> this.modelmapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDto;
	}

}

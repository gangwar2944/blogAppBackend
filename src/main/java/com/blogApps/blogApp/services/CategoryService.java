package com.blogApps.blogApp.services;

import java.util.List;

import com.blogApps.blogApp.payload.CategoryDto;
import org.springframework.stereotype.Service;


@Service
public interface CategoryService {

//	create
	public CategoryDto createCategory(CategoryDto categoryDto);
//	update
	public CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);
//	delete
	void deleteCategory(Long categoryId);
//	get
	public CategoryDto getCategoryById(Long categoryId); 
//	getAll
	
	public List<CategoryDto> getAllCategories();
}

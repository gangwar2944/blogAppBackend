package com.blogApps.blogApp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.blogApps.blogApp.entity.Category;
import com.blogApps.blogApp.exceptions.ResourceNotFoundException;
import com.blogApps.blogApp.payload.CategoryDto;
import com.blogApps.blogApp.repository.CategoryRepo;
import com.blogApps.blogApp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat= this.modelMapper.map(categoryDto, Category.class);
		Category addedCat= this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updateCategory= this.categoryRepo.save(cat) ;
		
		return this.modelMapper.map(updateCategory,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		
		Category category= this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		
		this.categoryRepo.delete(category);
		
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		Category category= this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		
		return this.modelMapper.map(category,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
	List<Category> categories=this.categoryRepo.findAll();
	
	List<CategoryDto> catDtos=categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}
	

}

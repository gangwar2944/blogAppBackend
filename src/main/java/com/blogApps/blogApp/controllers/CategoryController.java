package com.blogApps.blogApp.controllers;

import java.util.List;

import com.blogApps.blogApp.payload.ApiResponse;
import com.blogApps.blogApp.payload.CategoryDto;
import com.blogApps.blogApp.services.CategoryService;
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


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
	private CategoryService categoryService;
    
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
    {
    	CategoryDto creatCategory= this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(creatCategory,HttpStatus.CREATED);
    	
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Long  categoryId)
    {
    	CategoryDto updatedCategory= this.categoryService.updateCategory(categoryDto,categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
    	
    }
    
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse>deleteCategory(@PathVariable Long categoryId)
    {
    	this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("this category has been deleted", true),HttpStatus.OK);
    	
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>getCategoryById(@PathVariable Long categoryId)
    {
    	CategoryDto categoryDto= this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    	
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>>  getAllCategories(){
    	List<CategoryDto> categories = this.categoryService.getAllCategories();
    	return ResponseEntity.ok(categories);
    }
}

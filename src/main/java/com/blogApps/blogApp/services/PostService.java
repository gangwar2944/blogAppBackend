package com.blogApps.blogApp.services;
import java.util.List;

import com.blogApps.blogApp.payload.PostDto;
import com.blogApps.blogApp.payload.PostResponse;
import org.springframework.stereotype.Service;


@Service
public interface PostService {
  
//	post data
	PostDto createPost(PostDto postDto, Long Id, Long categoryId);
	
//	update data
	PostDto updatePost(PostDto postDto,Long postId);
	
//	delete data 
	void deletePost(Long postId);
	
//	get single post
	
	PostDto getPostById(Long postId);
	
//	getAll post At a time 
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
//	get all posts by category
	
	List<PostDto> getPostByCategory(Long categoryId);
	
//	get posts by user
	
	 List<PostDto> getPostByUser(Long userId); 
	 
//	 search posts
	 
	 List<PostDto> searchPosts(String keyword);

	
	
	
}

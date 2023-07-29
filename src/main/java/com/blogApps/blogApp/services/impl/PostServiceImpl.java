package com.blogApps.blogApp.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import com.blogApps.blogApp.entity.Category;
import com.blogApps.blogApp.entity.Post;
import com.blogApps.blogApp.entity.User;
import com.blogApps.blogApp.exceptions.ResourceNotFoundException;
import com.blogApps.blogApp.payload.PostDto;
import com.blogApps.blogApp.payload.PostResponse;
import com.blogApps.blogApp.repository.CategoryRepo;
import com.blogApps.blogApp.repository.PostRepo;
import com.blogApps.blogApp.repository.UserRepository;
import com.blogApps.blogApp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
	private PostRepo postRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepo categoryRepo;
    
	@Override
	public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {
		
		User user =this.userRepository.findById(userId)
				 .orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
		
		Category category= this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		
		Post post= this.modelMapper.map(postDto, Post.class);
		post.setImgName("derfault.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
		
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long postId) {

		Post post=this.postRepo.findById(postId)
			     .orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImgName(postDto.getImgName());
		Post updatedpost= this.postRepo.save(post);
		
		return this.modelMapper.map(updatedpost,PostDto.class);
	}

	@Override
	public void deletePost(Long postId) {
	Post post=this.postRepo.findById(postId)
		     .orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
	this.postRepo.delete(post);

	}

	@Override
	public PostDto getPostById(Long postId) {
	Post post=this.postRepo.findById(postId)
		 .orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy, String sortDir) {
		
		Sort sort =null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort  = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
	
		Pageable p = PageRequest.of(pageNumber,pageSize,sort);		
		 Page<Post> pagePost =  this.postRepo.findAll(p);
		
	     List<Post> allPosts=pagePost.getContent(); 
	     
	     List<PostDto> postDtos= allPosts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
	     
	     PostResponse postResponse = new PostResponse();
	     
	     postResponse.setContent(postDtos);
	     postResponse.setPageNumber(pagePost.getNumber());
	     postResponse.setPageSize(pagePost.getSize());
	     postResponse.setTotalElements(pagePost.getTotalElements());
		
	     postResponse.setTotalPages(pagePost.getTotalPages());
	     postResponse.setLastPage(pagePost.isLast());
	     
	     return postResponse;
	}

	@Override
	public List<PostDto> getPostByCategory(Long categoryId) {
	
		Category cat= this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		List<PostDto>postDtos=posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Long userId) {
	
		User user= this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","user id",userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}

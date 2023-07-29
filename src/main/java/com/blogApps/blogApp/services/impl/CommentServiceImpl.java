package com.blogApps.blogApp.services.impl;
import com.blogApps.blogApp.entity.Comment;
import com.blogApps.blogApp.entity.Post;
import com.blogApps.blogApp.exceptions.ResourceNotFoundException;
import com.blogApps.blogApp.payload.CommentDto;
import com.blogApps.blogApp.repository.CommentRepo;
import com.blogApps.blogApp.repository.PostRepo;
import com.blogApps.blogApp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Long postId) {
		
//		System.out.println(postId);

		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId",postId));
		
		
		
		Comment comment = this.modelMapper.map(commentDto,Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment= this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Long commentId) {
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","commentId",commentId));
		 
		this.commentRepo.delete(comment);

	}

}

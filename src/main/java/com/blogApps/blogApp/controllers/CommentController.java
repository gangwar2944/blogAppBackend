package com.blogApps.blogApp.controllers;

import com.blogApps.blogApp.payload.ApiResponse;
import com.blogApps.blogApp.payload.CommentDto;
import com.blogApps.blogApp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commetService;
     
	    @PostMapping("posts/{postId}/comments")
		public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long postId){
	    	
	    	CommentDto createComment = this.commetService.createComment(commentDto, postId);
	    	
			return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
			
	    }
	    
	    @DeleteMapping("/comments/{commentId}")
	    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long CommentId){
	    	this.commetService.deleteComment(CommentId);
	    	
	    	return new ResponseEntity<ApiResponse>(new ApiResponse("deleted", true),HttpStatus.OK);
	    }
}

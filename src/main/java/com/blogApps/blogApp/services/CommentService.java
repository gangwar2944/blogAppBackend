package com.blogApps.blogApp.services;

import com.blogApps.blogApp.payload.CommentDto;
import org.springframework.stereotype.Service;


@Service
public interface CommentService {
	
    CommentDto createComment(CommentDto commentDto, Long postId);
    
    void deleteComment(Long commentId);

}

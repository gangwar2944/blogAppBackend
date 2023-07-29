package com.blogApps.blogApp.repository;

import com.blogApps.blogApp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepo extends JpaRepository<Comment,Long>{

}

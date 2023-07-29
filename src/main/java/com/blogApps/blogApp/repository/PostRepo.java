package com.blogApps.blogApp.repository;
import java.util.List;

import com.blogApps.blogApp.entity.Category;
import com.blogApps.blogApp.entity.Post;
import com.blogApps.blogApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}

package com.blogApps.blogApp.repository;


import com.blogApps.blogApp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {

}

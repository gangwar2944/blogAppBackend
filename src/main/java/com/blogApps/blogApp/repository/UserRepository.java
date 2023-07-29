package com.blogApps.blogApp.repository;

import java.util.Optional;

import com.blogApps.blogApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}

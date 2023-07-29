package com.blogApps.blogApp.services;

import com.blogApps.blogApp.payload.UserDto;

import java.util.List;



public interface UserService {
  
//	 UserDto registerNewuser(UserDto user);
	
	 UserDto createUser(UserDto user);
	 
	 UserDto updateUser(UserDto user,Long userId);
	 
	 UserDto getUserById(Long userId);
	 
	 List<UserDto> getAllUser();
	 
	 void deleteUser(Long userId);
	
}

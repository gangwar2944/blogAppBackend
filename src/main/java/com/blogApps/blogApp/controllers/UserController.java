package com.blogApps.blogApp.controllers;

import java.util.List;



import com.blogApps.blogApp.payload.ApiResponse;
import com.blogApps.blogApp.payload.UserDto;
import com.blogApps.blogApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
    @Autowired
	private UserService userService;
    
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto userDto){
    	UserDto createUserDto= this.userService.createUser(userDto);
    	
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
    	
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Validated @RequestBody UserDto userDto,@PathVariable Long userId){
    	UserDto  updatedUserDto= this.userService.updateUser(userDto, userId);
		return new ResponseEntity<>(updatedUserDto,HttpStatus.CREATED);
    	
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Long userId) {
    	this.userService.deleteUser(userId);
    	return new ResponseEntity<ApiResponse>(new ApiResponse("user delete successfully",true),HttpStatus.OK);
    }
	
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId){
    	return ResponseEntity.ok(this.userService.getUserById(userId));
    }
    
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
    	return ResponseEntity.ok(this.userService.getAllUser());
    }
}

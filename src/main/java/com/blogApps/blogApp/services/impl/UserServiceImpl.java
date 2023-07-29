package com.blogApps.blogApp.services.impl;

import java.util.List;
import java.util.stream.Collectors;


import com.blogApps.blogApp.config.AppConstants;
import com.blogApps.blogApp.entity.Role;
import com.blogApps.blogApp.entity.User;
import com.blogApps.blogApp.exceptions.ResourceNotFoundException;
import com.blogApps.blogApp.payload.UserDto;
import com.blogApps.blogApp.repository.UserRepository;
import com.blogApps.blogApp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User sevedUser = userRepository.save(user);
		return this.userToDto(sevedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
	 User user =this.userRepository.findById(userId)
			 .orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
	 user.setFirstName(userDto.getName());
	 user.setEmail(userDto.getEmail());
	 user.setPassword(userDto.getPassword());
//	 user.setAbout(userDto.getAbout());
	 
	 User sevedUser = this.userRepository.save(user);
	 
	 UserDto userDto1 = this.userToDto(sevedUser);
	
		return userDto1;
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("user","id",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
	  List<User> users=this.userRepository.findAll();
	List<UserDto> userDtos = users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList()); 
		return userDtos;
	}

	@Override
	public void deleteUser(Long userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("user","Id" ,userId));
		   this.userRepository.delete(user);

	}
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
		
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto= this.modelMapper.map(user,UserDto.class);
		
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

//	@Override
//	public UserDto registerNewuser(UserDto userDto) {
//
//      User user= this.modelMapper.map(userDto, User.class);
//
////      encoded the password
//      user.setPassword(this.passwordEncoder.encode(user.getPassword()));
//
//      Role role= roleRepo.findById(AppConstants.NORMAL_USER).get();
//
////       user.getRole().add(role);
//
//      User newUser = this.userRepository.save(user);
//
//		return this.modelMapper.map(newUser, UserDto.class);
//	}

}

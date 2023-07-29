package com.blogApps.blogApp.security;

import com.blogApps.blogApp.entity.User;
import com.blogApps.blogApp.exceptions.ResourceNotFoundException;
import com.blogApps.blogApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("userName","userEmail",username));
		
		return user;
	}
	

}

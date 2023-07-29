package com.blogApps.blogApp;

import com.blogApps.blogApp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableAutoConfiguration
@ComponentScan(basePackages = "com.blogApps.blogApp.repository.*")
@SpringBootApplication
public class BlogAppApplication{
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;


	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//
//		try {
//
//			Role role =new Role();
//			role.setId(AppConstants.ADMIN_USER);
//			role.setName("ADMIN_USER");
//
//			Role role1 = new Role();
//			role1.setId(AppConstants.NORMAL_USER);
//			role1.setName("NORMAL_USER");
//
//			List<Role> roles= List.of(role,role1);
//
//			List<Role> result= this.roleRepo.saveAll(roles);
//
//			result.forEach(r->System.out.println(r.getName()));
//		}catch(Exception e) {
//			e.printStackTrace();
//		}


}

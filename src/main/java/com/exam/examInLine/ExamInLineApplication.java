package com.exam.examInLine;

import com.exam.examInLine.helper.UserFoundException;
import com.exam.examInLine.model.Role;
import com.exam.examInLine.model.User;
import com.exam.examInLine.model.UserRole;
import com.exam.examInLine.service.UserService;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class ExamInLineApplication implements CommandLineRunner {
		
	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ExamInLineApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

		try {
			System.out.println("Starting Code");


			User user = new User();

			user.setFirstName("adama");
			user.setLastName("NDIAYE");
			user.setUsername("adama1818");
			user.setPassword(this.bCryptPasswordEncoder.encode("adama123"));
			user.setEmail("awa@gmail.com");
			user.setProfile("default.png");

			Role role1 = new Role();
			role1.setRoleId(44L);
			role1.setRoleName("ADMIN");


			Set<UserRole> userRoleSet = new HashSet<>();

			UserRole userRole = new UserRole();

			userRole.setRole(role1);

			userRole.setUser(user);

			userRoleSet.add(userRole);


			User user1 = this.userService.createUser(user, userRoleSet);
			System.out.println(user1.getUsername());
		}catch(UserFoundException e){
			e.printStackTrace();
		}
	}

}

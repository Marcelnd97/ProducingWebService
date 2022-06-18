package com.examen.examenEnLigne;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.examen.examenEnLigne.model.Role;
import com.examen.examenEnLigne.model.User;
import com.examen.examenEnLigne.model.UserRole;
import com.examen.examenEnLigne.service.UserService;

@SpringBootApplication
public class ExamenEnLigneApplication implements CommandLineRunner {
		
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(ExamenEnLigneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Starting Code");
		
		
		/*User user = new User();
		
		user.setFirstName("Mouna");
		user.setLastName("SARR");
		user.setUsername("mounas1818");
		user.setPassword("moun123");
		user.setEmail("mounas@gmail.com");
		user.setProfile("default.png");
		
		Role role1 = new Role();
		role1.setRoleId(45L);
		role1.setRoleName("NORMAL");
		
		
		Set<UserRole> userRoleSet = new HashSet<>();
		
		UserRole userRole = new UserRole();
		
		userRole.setRole(role1);
		
		userRole.setUser(user);
		
		userRoleSet.add(userRole);
		
		
		User user1 = this.userService.createUser(user, userRoleSet);
		System.out.println(user1.getUsername());*/
		
	}

}

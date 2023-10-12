package com.exam.examInLine.controller;

import java.util.HashSet;
import java.util.Set;

import com.exam.examInLine.helper.ErrorResponse;
import com.exam.examInLine.helper.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import com.exam.examInLine.model.Role;
import com.exam.examInLine.model.User;
import com.exam.examInLine.model.UserRole;
import com.exam.examInLine.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//création d'un utilisateur
	@PostMapping("/add")
	public User createUser(@RequestBody User user) throws Exception {

		user.setProfile("default.png");

		//Encodage du mot de passe par bcrytPasswordEncoder

		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

		Set<UserRole> roles = new HashSet<>();
		
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		
		return this.userService.createUser(user, roles);
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		
		return this.userService.getUser(username);
		
	}
	
	//supprimer l'utilisateur par son id
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
	}
		
	
	
	@ExceptionHandler({UserFoundException.class})
    public ResponseEntity<ErrorResponse> notFound(UserFoundException ex){

        return new ResponseEntity<ErrorResponse>(
            new ErrorResponse(ex.getMessage(), 401, "The user was  found") , HttpStatus.FOUND);
    }
	
	
	 
	
}
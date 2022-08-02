package com.exam.examInLine.controller;

import com.exam.examInLine.configu.JwtUtils;
import com.exam.examInLine.helper.UserNotFoundException;
import com.exam.examInLine.model.JwtRequest;
import com.exam.examInLine.model.JwtResponse;
import com.exam.examInLine.model.User;
import com.exam.examInLine.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;


	@Autowired
	private JwtUtils jwtUtil;
	 
	//generation de jeton
	

	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try {
			
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

			
		}catch(UserNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found");
		}
		
		///Authenticate
		
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	
		
	} 
	
	
	private   void authenticate(String username, String password) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));


		}catch(DisabledException e) {
			throw new Exception("User disable " + e.getMessage());
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid Credential" + e.getMessage());
		}
	}

	//Afficherge des detailles de l'utilisateur courant

	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
	}

}

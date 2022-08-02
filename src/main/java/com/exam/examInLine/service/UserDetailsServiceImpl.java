package com.exam.examInLine.service;

import com.exam.examInLine.model.User;
import com.exam.examInLine.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = this.userRepository.findByUsername(username);
		if(user == null) {
			System.out.println("Utilisateur introuvable !!");
			throw new UsernameNotFoundException("Aucun utilisateur n'est retrouvé !!");
		}
		
		return user;
	}

}

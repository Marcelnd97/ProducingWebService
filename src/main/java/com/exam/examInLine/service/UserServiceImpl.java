package com.exam.examInLine.service;

import java.util.Set;

import com.exam.examInLine.helper.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examInLine.model.User;
import com.exam.examInLine.model.UserRole;
import com.exam.examInLine.repo.RoleRepository;
import com.exam.examInLine.repo.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository; 
	
	//Création d'un utilisateur
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local = this.userRepository.findByUsername(user.getUsername());
		
		if(local != null)
		{
			System.out.println("User is already there !!");
			throw new UserFoundException("User already present !!");
		}else
		{
			//créer un utilistateur
			
			for(UserRole ur:userRoles)
			{
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
			
		}
		
		return local;
	}

	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
	}
	
	

}

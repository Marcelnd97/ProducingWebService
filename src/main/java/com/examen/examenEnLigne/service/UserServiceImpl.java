package com.examen.examenEnLigne.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.examenEnLigne.model.User;
import com.examen.examenEnLigne.model.UserRole;
import com.examen.examenEnLigne.repo.RoleRepository;
import com.examen.examenEnLigne.repo.UserRepository;


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
			throw new Exception("User already present !!");
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

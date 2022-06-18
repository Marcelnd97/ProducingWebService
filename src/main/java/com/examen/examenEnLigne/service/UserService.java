package com.examen.examenEnLigne.service;

import java.util.Set;

import com.examen.examenEnLigne.model.User;
import com.examen.examenEnLigne.model.UserRole;

public interface UserService {

	//Création d'un utilisateur
	
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//afficher l'utilisateur par le username
	
	public User getUser(String username);
	
	//supprimer l'utilisateur par son id
	
	public void deleteUser(Long userId);

	
}

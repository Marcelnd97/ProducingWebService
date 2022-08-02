package com.exam.examInLine.helper;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(){
		super("Cet utilisateur n'est pas dans la base de donnée !!");
	}

	public UserNotFoundException(String message) {
	        super(message);
	    }
}

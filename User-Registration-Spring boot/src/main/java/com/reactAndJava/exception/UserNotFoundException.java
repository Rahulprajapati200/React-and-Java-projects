package com.reactAndJava.exception;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(Long id) {
		super("Could not found user by id-"+id);
	}

}

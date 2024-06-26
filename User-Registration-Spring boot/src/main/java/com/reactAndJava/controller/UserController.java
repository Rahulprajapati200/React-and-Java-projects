package com.reactAndJava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reactAndJava.exception.UserNotFoundException;
import com.reactAndJava.model.User;
import com.reactAndJava.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/user")
  User newUser(@RequestBody User newUser) {
	  return userRepository.save(newUser);
  }
	
	@GetMapping("/users")
	  List<User> getAllUsers() {
		  return userRepository.findAll();
	  }
	
	@GetMapping("/user/{id}")
	  User newUser(@PathVariable Long id) {
		  return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
	  }
	@PutMapping("/updateUser/{id}")
	User updateUser(@RequestBody User newUser,@PathVariable Long id ) {
		return  userRepository.findById(id)
				.map(user->{
			user.setEmail(newUser.getEmail());
			user.setName(newUser.getName());
			user.setUsername(newUser.getUsername());
			return userRepository.save(user); 
		}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	@DeleteMapping("/deleteUser/{id}")
	  String deleteUser(@PathVariable Long id) {
		
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return "User with id -"+id+" has been deleted successfully.";
	  }
}

package com.api.merryba.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.merryba.Model.User;
import com.api.merryba.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	//Create User
	//@RequestBody annotation
	//@PostMapping
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	
	//Get User by id
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id")Long id) {
		return userService.getUserById(id);
		
	}
	
	//Get User by id
	@GetMapping("/users/byusername/{username}")
	public Optional<User> getUserById(@PathVariable("username")String username) {
		return userService.getUserByUsername(username);
			
	}	
	
	
	//Update user by Id
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id")Long id,@RequestBody User user) {
		return userService.updateUserById(id, user);
	}
	
	//Delete User By Id
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id")Long id) {
		userService.deleteUserById(id);
	}

}

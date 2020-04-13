package com.api.merryba.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.merryba.Model.User;
import com.api.merryba.exceptions.UserExistsException;
import com.api.merryba.exceptions.UserNameNotFoundException;
import com.api.merryba.exceptions.UserNotFoundException;
import com.api.merryba.service.UserService;

@RestController
@Validated
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
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user,UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("users/{id}").buildAndExpand(user.getId()).toUri());;
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}
		catch(UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	
	//Get User by id
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id")@Min(1)Long id) {
		try {
		return userService.getUserById(id);
		}
		catch(UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
	}
	
	//Get User by id
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username")String username) throws UserNameNotFoundException {
		
		User user = userService.getUserByUsername(username);
		if(user==null) throw new UserNameNotFoundException("Username: '"+username+ "' not found in User Repository");
		return user;
		
			
	}	
	
	
	//Update user by Id
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id")Long id,@RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		}
		catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	//Delete User By Id
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id")Long id) {
		userService.deleteUserById(id);
	}

}

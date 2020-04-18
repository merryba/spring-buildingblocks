package com.merryba.api.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.merryba.api.Model.User;
import com.merryba.api.exceptions.UserExistsException;
import com.merryba.api.exceptions.UserNameNotFoundException;
import com.merryba.api.exceptions.UserNotFoundException;
import com.merryba.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags="User Management Restful Services",value="UserController",description ="Controller for User Service")
@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	@ApiOperation(value="Retrieve list of users")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	// Create User
	// @RequestBody annotation
	// @PostMapping
	@ApiOperation(value="Creates a new user")
	@PostMapping
	public ResponseEntity<Void> createUser(@ApiParam("user information for a new user to be craeted")@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("users/{id}").buildAndExpand(user.getId()).toUri());
			;
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	// Get User by id
	@ApiOperation(value="Retrieve user by userId")
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			return user;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}

	// Update user by Id
	@ApiOperation(value="Updates user by userId")
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	// Delete User By Id
	@ApiOperation(value="Delete user by userId")
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	// getUserByUsername
	@ApiOperation(value="Retrieve user by username")
	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
		User user = userService.getUserByUsername(username);
		if (user == null)
			throw new UserNameNotFoundException("Username: '" + username + "' not found in User repository");
		return user;

	}

}

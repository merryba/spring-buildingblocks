package com.api.merryba.Controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.merryba.Model.User;
import com.api.merryba.Model.Views;
import com.api.merryba.exceptions.UserNotFoundException;
import com.api.merryba.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@Validated
@RequestMapping(value = "/jsonview/users")
public class UserJsonViewController {

	@Autowired
	private UserService userService;

	// Get User by id - External
		@GetMapping("/external/{id}")
		@JsonView(Views.External.class)
		public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
			try {
				return userService.getUserById(id);
			} catch (UserNotFoundException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}

		}
		// Get User by id - Internal
		@GetMapping("/internal/{id}")
		@JsonView(Views.Internal.class)
		public Optional<User> getUserByIdInternal(@PathVariable("id") @Min(1) Long id) {
			try {
				return userService.getUserById(id);
			} catch (UserNotFoundException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}

		}

}

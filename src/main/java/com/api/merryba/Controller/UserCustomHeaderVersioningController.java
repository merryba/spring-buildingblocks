package com.api.merryba.Controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.merryba.Model.User;
import com.api.merryba.dtos.UserDtoV1;
import com.api.merryba.dtos.UserDtoV2;
import com.api.merryba.exceptions.UserNotFoundException;
import com.api.merryba.service.UserService;

@RestController
@RequestMapping(value = "versioning/header/users")
public class UserCustomHeaderVersioningController {
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;

	// Custom Header Versioning- V1
	@GetMapping(value = "/{id}", headers = "API-VERSION=1")
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> userOptional = userService.getUserById(id);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found");

		User user = userOptional.get();

		UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
		return userDtoV1;
	}

	// Custom Header Versioning -V2
	@GetMapping(value = "/{id}", headers = "API-VERSION=2")
	public UserDtoV2 getUserByIdV2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

		Optional<User> userOptional = userService.getUserById(id);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found");

		User user = userOptional.get();

		UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
		return userDtoV2;
	}

}

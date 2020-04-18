package com.merryba.api.Controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merryba.api.Model.User;
import com.merryba.api.dtos.UserDtoV1;
import com.merryba.api.dtos.UserDtoV2;
import com.merryba.api.exceptions.UserNotFoundException;
import com.merryba.api.service.UserService;

@RestController
@RequestMapping(value="versioning/params/users")
public class UserRequestParameterVersioningController {
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
	//Request Param Versioning -v1
	@GetMapping(value="/{id}",params = "version=1")
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		
			Optional<User> userOptional = userService.getUserById(id);
			if(!userOptional.isPresent()) throw new UserNotFoundException("User not found");
		

			User user = userOptional.get();
			
			UserDtoV1 userDtoV1 = modelMapper.map(user,UserDtoV1.class);
			return userDtoV1;
	}
	
	// Request Param Versioning -v2
	@GetMapping(value="/{id}",params = "version=2")
	public UserDtoV2 getUserByIdV2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		
			Optional<User> userOptional = userService.getUserById(id);
			if(!userOptional.isPresent()) throw new UserNotFoundException("User not found");
		

			User user = userOptional.get();
			
			UserDtoV2 userDtoV2 = modelMapper.map(user,UserDtoV2.class);
			return userDtoV2;
	}
	
	
}

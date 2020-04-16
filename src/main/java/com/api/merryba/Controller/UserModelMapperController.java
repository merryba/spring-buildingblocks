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
import com.api.merryba.dtos.UserMmDto;
import com.api.merryba.exceptions.UserNotFoundException;
import com.api.merryba.service.UserService;

@RestController
@RequestMapping(value="/modelmapper/users")
public class UserModelMapperController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
	// Get User by id
	@GetMapping("/{id}")
	public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		
			Optional<User> userOptional = userService.getUserById(id);
			if(!userOptional.isPresent()) throw new UserNotFoundException("User not found");
		

			User user = userOptional.get();
			
			UserMmDto userDto = modelMapper.map(user,UserMmDto.class);
			return userDto;
	}

}

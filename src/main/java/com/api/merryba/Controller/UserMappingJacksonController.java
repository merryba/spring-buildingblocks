package com.api.merryba.Controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.merryba.Model.User;
import com.api.merryba.exceptions.UserNotFoundException;
import com.api.merryba.service.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping(value = "/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

	@Autowired
	private UserService userService;

	// Get User by id - fields with HashSet
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			Set<String> fields = new HashSet<String>();
			fields.add("id");
			fields.add("username");
			fields.add("ssn");
			fields.add("orders");
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));

			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
	
	// Get User by id - fields with RequestParam
		@GetMapping("/params/{id}")
		public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id,
				@RequestParam  Set<String> fields) {
			try {
				Optional<User> userOptional = userService.getUserById(id);
				User user = userOptional.get();
				
				FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
						SimpleBeanPropertyFilter.filterOutAllExcept(fields));

				MappingJacksonValue mapper = new MappingJacksonValue(user);
				mapper.setFilters(filterProvider);
				return mapper;
			} catch (UserNotFoundException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}

		}

}

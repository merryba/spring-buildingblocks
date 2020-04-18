package com.merryba.api.Controller;

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

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.merryba.api.Model.Employee;
import com.merryba.api.Model.Views;
import com.merryba.api.exceptions.UserNotFoundException;
import com.merryba.api.service.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
@Validated

public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	// Get User by id - fields with HashSet
	// Get User by id - External
			@GetMapping("/external/{id}")
			@JsonView(Views.External.class)
			public Optional<Employee> getEmployeeById(@PathVariable("id") @Min(1) Long id) {
				System.out.println("id"+id);
				try {
					return employeeService.getEmployeeById(id);
				} catch (UserNotFoundException e) {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
				}

			}
			// Get User by id - Internal
			@GetMapping("/internal/{id}")
			@JsonView(Views.Internal.class)
			public Optional<Employee> getEmployeeByIdInternal(@PathVariable("id") @Min(1) Long id) {
				try {
					return employeeService.getEmployeeById(id);
				} catch (UserNotFoundException e) {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
				}

			}

}

package com.merryba.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.merryba.api.Model.Employee;
import com.merryba.api.Model.User;
import com.merryba.api.exceptions.UserNotFoundException;
import com.merryba.api.repositories.EmployeeRepository;

@RestController
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	// Get Employee by Id
	public Optional<Employee> getEmployeeById(Long id) throws UserNotFoundException {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if (!employeeOptional.isPresent())
			throw new UserNotFoundException("User not found in teh repository");
		return employeeOptional;
	}

}

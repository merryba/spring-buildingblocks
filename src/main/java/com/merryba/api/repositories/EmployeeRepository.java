package com.merryba.api.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.merryba.api.Model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	public Employee findByname(String name);

}

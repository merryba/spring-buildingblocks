package com.api.merryba.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.merryba.Model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	public Employee findByname(String name);

}

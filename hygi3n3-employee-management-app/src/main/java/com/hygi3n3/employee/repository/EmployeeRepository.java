package com.hygi3n3.employee.repository;

import com.hygi3n3.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
	
	// add a method to sort by last name
	List<Employee> findAllByOrderByLastNameAsc();
	
	// search by name
	List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String name, String lName);

}

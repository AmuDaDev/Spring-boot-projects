package com.hygi3n3.employee.repository;

import com.hygi3n3.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findAllByOrderByLastNameAsc();
	List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String name, String lName);
}

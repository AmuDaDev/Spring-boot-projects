package com.hygi3n3.employee.service;

import com.hygi3n3.employee.model.Employee;

import java.util.List;

public interface EmployeeService {
	List<Employee> findAll();
	List<Employee> findAllByOrderByLastNameAsc();
	Employee findById(int theId);
	void save(Employee theEmployee);
	void deleteById(int theId);
	List<Employee> searchBy(String theName);
}

package com.hygi3n3.employee.controller;

import com.hygi3n3.employee.model.Employee;
import com.hygi3n3.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> theEmployees = employeeService.findAllByOrderByLastNameAsc();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		return "/employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		// create model attribute to bind form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);
		return "/employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		Employee theEmployee = employeeService.findById(theId);
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		return "/employees/employee-form";			
	}
	
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		employeeService.save(theEmployee);
		return "redirect:/employees/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		employeeService.deleteById(theId);
		return "redirect:/employees/list";
		
	}
	
	@GetMapping("/search")
	public String delete(@RequestParam("employeeName") String theName, Model theModel) {
		List<Employee> theEmployees = employeeService.searchBy(theName);
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		return "/employees/list-employees";
	}
	
}



















package com.example.smallwebapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.smallwebapp.model.Employee;
import com.example.smallwebapp.service.EmployeeServiceImpl;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	@GetMapping
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees",employeeServiceImpl.getAllEmployees());
		
		return "index";
	}
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		//create model attribute to bind from data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		System.out.println("coming...........");
		return "new_employee"; 
	}
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		//save Employee to database
		employeeServiceImpl.saveEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id,Model model) {
		//get Employee from the service
		Employee employee=employeeServiceImpl.getEmployeeById(id);
		//set Employees as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
		
	}
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployeeById(@PathVariable(value="id") long id,Model model) {
		this.employeeServiceImpl.deleteEmployeeById(id);
		return "redirect:/";
	}
}

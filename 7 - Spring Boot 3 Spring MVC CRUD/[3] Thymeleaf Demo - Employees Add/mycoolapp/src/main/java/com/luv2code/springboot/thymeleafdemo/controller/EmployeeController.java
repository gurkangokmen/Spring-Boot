package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// get the employees from db
		List<Employee> theEmployees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		// it has to match up with /employees/list-employees and it'll match up with that given file
		//src/main/resources/templates/employees/list-employees.html
		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Employee theEmployee = new Employee();

		theModel.addAttribute("employee", theEmployee);

		// Every "Model theModel" is different & Bind is valid method scope
		// System.out.println(theModel.getAttribute("employees")); ===> null

		//src/main/resources/templates/employees/employee-form.html
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) { //@ModelAttribute("employee") Employee theEmployee = Form data passed in

		// save the employee
		employeeService.save(theEmployee);

		// use a redirect to prevent duplicate submissions (we want to prevent duplicate submissions in case s(he) clicks on page reload)
		// "Post/Redirect/Get" pattern
		return "redirect:/employees/list"; //Redirect to request mapping employees/list
	}
}










package com.sasi.spring.api.mockitodemo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sasi.spring.api.mockitodemo.exception.EmployeeCustamizedException;
import com.sasi.spring.api.mockitodemo.model.Employee;
import com.sasi.spring.api.mockitodemo.model.Response;
import com.sasi.spring.api.mockitodemo.service.EmployeeService;
import com.sasi.spring.api.mockitodemo.util.PayloadValidator;


@RestController
public class EmployeeController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/Employee")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
		
	}
	@GetMapping("/Employee/{id}")
	public ResponseEntity<Employee> getAllEmployeeById(@PathVariable int id) throws EmployeeCustamizedException{
		logger.info("Employee id to return " + id);
		Employee employee	=employeeService.getEmployeeById(id);
		if (employee == null || employee.getId() <= 0){
            throw new EmployeeCustamizedException("Employee doesn´t exist");
    	}
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);
	}
	@DeleteMapping("/Employee/{id}")
	public ResponseEntity<Response> removeEmployeeById(@PathVariable("id") int id) throws EmployeeCustamizedException{
		logger.info("Employee id to return " + id);
		Employee employee	=employeeService.getEmployeeById(id);
		if (employee == null || employee.getId() <= 0){
            throw new EmployeeCustamizedException("Employee doesn´t exist");
    	}
		employeeService.removeEmployee(employee);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Employee has been deleted"), HttpStatus.OK);
	}
	@PostMapping("/employee")
	public ResponseEntity<Employee> SaveEmployee(@RequestBody Employee employee) throws EmployeeCustamizedException{
		logger.info("Employee to save " + employee);
    	if (!PayloadValidator.validateCreatePayload(employee)){
            throw new EmployeeCustamizedException("employee malformed, id must not be defined");
    	}
    	return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.OK);
	}
	
	@PatchMapping("/employee")
	public ResponseEntity<Employee> UpdateEmployee(@RequestBody Employee employee) throws EmployeeCustamizedException{
		logger.info("Employee update " + employee);
		Employee empResult=employeeService.getEmployeeById(employee.getId());
		if (empResult == null || empResult.getId() <= 0){
            throw new EmployeeCustamizedException("employee to update doesn´t exist");
    	}
    	
    	return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.OK);
	}


}

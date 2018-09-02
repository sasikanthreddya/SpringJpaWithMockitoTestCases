package com.sasi.spring.api.mockitodemo.service;

import java.util.List;

import com.sasi.spring.api.mockitodemo.model.Employee;

public interface EmployeeService {
	
public List<Employee> getAllEmployees();
public Employee getEmployeeById(int id);
public Employee saveEmployee(Employee employee);
public void removeEmployee(Employee employee);


}

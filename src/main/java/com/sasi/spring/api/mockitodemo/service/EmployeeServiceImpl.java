package com.sasi.spring.api.mockitodemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasi.spring.api.mockitodemo.model.Employee;
import com.sasi.spring.api.mockitodemo.repositry.EmployeeRepo;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return (List<Employee>) employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		return employeeRepo.findById(id);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public void removeEmployee(Employee employee) {
		employeeRepo.delete(employee);
	}
	

}

package com.sasi.spring.api.mockitodemo.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sasi.spring.api.mockitodemo.model.Employee;


@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {
	Employee findById(int id);
	
}

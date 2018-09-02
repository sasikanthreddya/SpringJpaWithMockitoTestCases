package com.sasi.spring.api.mockitodemo.servive;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sasi.spring.api.mockitodemo.model.Employee;
import com.sasi.spring.api.mockitodemo.repositry.EmployeeRepo;
import com.sasi.spring.api.mockitodemo.service.EmployeeServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {
	@Mock
	private EmployeeRepo employeeRepo;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testGetAllToDo(){
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee(1, "ram", "IT", 25.0));
		employeeList.add(new Employee(2, "seta", "Network", 25.0));
		when(employeeRepo.findAll()).thenReturn(employeeList);
		
		List<Employee> resultemployeeList =employeeService.getAllEmployees();
		assertEquals(2, resultemployeeList.size());
		
	}
	@Test
	public void testGetEmployeeById(){
		Employee employee=new Employee(1, "ram", "IT", 25.0);
		when(employeeRepo.findById(1)).thenReturn(employee);
		
		Employee resultEmployee=employeeService.getEmployeeById(1);
	 	assertEquals(1, resultEmployee.getId());
		assertEquals("ram", resultEmployee.getName());
		
	}
	@Test
	public void removeEmployee(){
		Employee employee=new Employee(1, "ram", "IT", 25.0);
		employeeService.removeEmployee(employee);
		verify(employeeRepo,times(1)).delete(employee);
		
	}
}

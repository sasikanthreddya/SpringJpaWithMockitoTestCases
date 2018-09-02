package com.sasi.spring.api.mockitodemo.servive.web;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sasi.spring.api.mockitodemo.MockitoWithDataJpaCurdApplication;
import com.sasi.spring.api.mockitodemo.model.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MockitoWithDataJpaCurdApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerTest {
	
   private MockMvc mockMvc;
	
   ObjectMapper om = new ObjectMapper();
   
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyAllEmployeeList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/Employee").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(3))).andDo(print());
	}
	@Test
	public void verifyEmployeeById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/Employee/2").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.dept").exists())
		.andExpect(jsonPath("$.salary").exists())
		.andExpect(jsonPath("$.id").value(2))
		.andExpect(jsonPath("$.name").value("Ram"))
		.andExpect(jsonPath("$.dept").value("IT"))
		.andExpect(jsonPath("$.salary").value(25.5))
		.andDo(print());
	}
	@Test
	public void verifyInvalidEmployeeArgument() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/Employee/f").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.errorCode").value(400))
			.andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
			.andDo(print());
	}
	@Test
	public void verifyInvalidEmployeeId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/Employee/9").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Employee doesn´t exist"))
		.andDo(print());
	}
	@Test
	public void verifyDeleteEmployee() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/Employee/4").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.message").value("Employee has been deleted"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidDeleteEmployeeverify() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/Employee/9").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Employee doesn´t exist"))
		.andDo(print());
	}
	@Test
	public void verifyEmployee() throws Exception {
		//Employee employee=new Employee(8, "geta", "IT", 55.0);
		Employee employee=new Employee();
		employee.setName("geta");
		employee.setDept("IT");
		employee.setSalary(55.0);
		
		String jsonRequest = om.writeValueAsString(employee);
		
		/*mockMvc.perform(MockMvcRequestBuilders.post("/Employee/")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.dept").exists())
				.andExpect(jsonPath("$.salary").exists())
				.andExpect(jsonPath("$.name").value("geta"))
				.andExpect(jsonPath("$.dept").value("IT"))
				.andExpect(jsonPath("$.salary").value(55.0))
				.andDo(print());*/
		
		String resultContent=mockMvc.perform(MockMvcRequestBuilders.post("/employee/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Employee resltEmp=om.readValue(resultContent, Employee.class);
		Assert.assertTrue(resltEmp.getName().equals("geta"));
		
	}
}

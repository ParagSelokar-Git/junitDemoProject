package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.entity.Employee;
import com.example.demo.exceptions.EmployeeNotPresent;
import com.example.demo.service.EmployeeService;
import com.google.gson.Gson;

//@SpringBootTest
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	EmployeeService employeeService;
	
	@InjectMocks
	EmployeeController employeeController;
	
	Employee emp;

	// JUnit test for getEmpById method
	@Test
	public void testGetEmpById() throws Exception {
		String url = "http://localhost:8080/employee/emp/5";
		when(employeeService.getEmpById(5L)).thenReturn(createEmployeeStub());
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		Mockito.verify(employeeService).getEmpById((long) 5);
	}

	// JUnit test for getAllEmployees method
	@Test
	public void testGetAllEmployees() throws Exception {
		String url = "http://localhost:8080/employee/all";
		when(employeeService.getAllEmployees()).thenReturn(createAllEmployeesStub());
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		Mockito.verify(employeeService).getAllEmployees();
	}

	// JUnit test for deleteEmployees method
	@Test
	public void testDeleteEmpById() throws Exception {
		String uri = "http://localhost:8080/employee/delete/1";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		Mockito.verify(employeeService).deleteEmpById((long) 1);
	}

	// JUnit test for addEmployee method
	@Test
	public void testAddEmployee() throws Exception {
		String uri = "http://localhost:8080/employee/save";
		Employee addEmp = new Employee((long) 5, "name-5");
		String inputJson = new Gson().toJson(addEmp);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		Mockito.verify(employeeService).addEmployee(addEmp);
	}

	private Employee createEmployeeStub() {
		Employee emp = new Employee((long) 5, "name-5");
		return emp;
	}

	public List<Employee> createAllEmployeesStub() {
		return IntStream.rangeClosed(1, 2).mapToObj(i -> new Employee((long) i, "name-" + i))
				.collect(Collectors.toList());
	}
}

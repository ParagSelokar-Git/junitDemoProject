package com.example.demo.controller;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

//@SpringBootTest
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {


	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EmployeeService employeeService;

	
	// JUnit test 	for getEmpById method
	@Test
	public void getEmpByIdTest() throws Exception {
		
		String url = "http://localhost:8080/employee/emp/5";
		
		when(employeeService.getEmpById(5L)).thenReturn(createEmployeeStub());
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)).andReturn();
    	Mockito.verify(employeeService).getEmpById((long) 5);
	}

	// JUnit test for getAllEmployees method
	@Test
    public void getAllEmployeesTest() throws Exception{
		String url = "http://localhost:8080/employee/all";
    	when(employeeService.getAllEmployees()).thenReturn(createAllEmployeesStub());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)).andReturn();
    	Mockito.verify(employeeService).getAllEmployees();
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

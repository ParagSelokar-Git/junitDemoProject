package com.example.demo.controller;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;

@SpringBootTest
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;
	
	@Mock
	EmployeeService employeeService;
	
	@Test
	public void getEmpByIdTest() {
		when(employeeService.getEmpById(5L)).thenReturn(createEmployeeStub());
		
		
	}
	
	private Employee createEmployeeStub(){
		Employee emp = new Employee((long) 5, "name-5");
		return emp;
	}
	
}

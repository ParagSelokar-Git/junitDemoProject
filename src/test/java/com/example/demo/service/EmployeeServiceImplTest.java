package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Employee;

@SpringBootTest
public class EmployeeServiceImplTest {

	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;
	
//	@Mock
	
	@Mock
	EmployeeDao employeeDao;
	
	// JUnit test for getEmpById method
	@Test
	public void getEmpByIdTest() {
		when(employeeDao.getEmpById(5L)).thenReturn(createEmployeeStub());
		
		Employee emp = employeeServiceImpl.getEmpById(5L);
		
		assertEquals(emp.getName(), "name-5");
	}
	
    // JUnit test for getAllEmployees method
    @Test
    public void getAllEmployeesTest(){
       
    	when(employeeDao.getAllEmployees()).thenReturn(createAllEmployeesStub());
        List<Employee> employeeList = employeeServiceImpl.getAllEmployees();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }
    
	
	private Employee createEmployeeStub(){
		Employee emp = new Employee((long) 5, "name-5");
		return emp;
	}
	
	public List<Employee> createAllEmployeesStub() {
		return IntStream.rangeClosed(1, 2).mapToObj(i -> new Employee((long) i, "name-" + i))
				.collect(Collectors.toList());
	}
}
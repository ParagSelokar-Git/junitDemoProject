package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Employee;
import com.example.demo.exceptions.EmployeeAlreadyPresent;
import com.example.demo.exceptions.EmployeeNotPresent;

@SpringBootTest
public class EmployeeServiceImplTest {

	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	EmployeeDao employeeDao;

	// JUnit test for getEmpById method
	@Test
	@DisplayName("Validate Employee by its id in system")
	public void testGetEmpById() {
		when(employeeDao.getEmpById(5L)).thenReturn(createEmployeeStub());
		Employee emp = employeeServiceImpl.getEmpById(5L);		
		assertEquals(emp.getId(), 5);
	}
	
	// JUnit test for getEmpById method
	@Test
	@DisplayName("Validate Employee by its name in system")
	public void testGetEmpByName() {
		when(employeeDao.getEmpById(5L)).thenReturn(createEmployeeStub());
		Employee emp = employeeServiceImpl.getEmpById(5L);		
		assertEquals(emp.getName(), "name-5");
	}
	// JUnit test for addEmp method
	@Test
	@DisplayName("Register new Employee in system")
	public void testAddEmp() {
		Employee addEmp = new Employee((long) 5, "name-5");
		
		when(employeeDao.addEmployee(addEmp)).thenReturn(createEmployeeStub());
		Employee emp = employeeServiceImpl.addEmployee(addEmp);		
		assertEquals(emp.getName(), "name-5");
	}
	
	@Test
	@DisplayName("Throws error if Employee existed in system")
	public void testAddExistingEmp() {
		when(employeeDao.getEmpById(5L)).thenReturn(createEmployeeStub());
		Employee addEmp = new Employee((long) 5, "name-5");
		assertThrows(EmployeeAlreadyPresent.class, () -> employeeServiceImpl.addEmployee(addEmp));
	}
	
	@Test
	@DisplayName("Throws error if Employee is not existed in system")
	public void testEmpNotExisted() {
		when(employeeDao.getEmpById(5L)).thenReturn(createEmployeeStub());
		assertThrows(EmployeeNotPresent.class, () -> employeeServiceImpl.getEmpById(52L));
	}

	// JUnit test for getAllEmployees method
	@Test
	@DisplayName("Get all Employee registerd in system")
    public void testGetAllEmployees(){
    	when(employeeDao.getAllEmployees()).thenReturn(createAllEmployeesStub());
        List<Employee> employeeList = employeeServiceImpl.getAllEmployees();
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
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

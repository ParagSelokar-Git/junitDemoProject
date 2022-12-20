package com.example.demo.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;

@Service
public class EmployeeDaoImpl implements EmployeeDao {
	
	public static List<Employee> employeeList;
	
	@Override
	public Employee addEmployee(Employee employee) {
		employeeList.add(employee);
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {

//		List<Employee> empList = new ArrayList<>();
//		empList.add(new Employee((long) 1, "name1"));
//		empList.add(new Employee((long) 2, "name2"));
//		empList.add(new Employee((long) 3, "name3"));
//		empList.add(new Employee((long) 4, "name4"));
//		empList.add(new Employee((long) 5, "name5"));
//		empList.add(new Employee((long) 6, "name6"));
//		return empList;

		return getAllEmployeesList();
	}

	@Override
	public Employee getEmpById(Long empidL) {
		List<Employee> empList = getAllEmployeesList();
		return (Employee) empList.stream().filter(i -> i.getId().equals(empidL)).findAny()                                      // If 'findAny' then return found
                .orElse(null);
	}

	@Override
	public void deleteEmpById(Long empidL) {
	}

	public static List<Employee> getAllEmployeesList() {
		
		employeeList = IntStream.rangeClosed(1, 20).mapToObj(i -> new Employee((long) i, "name-" + i))
				.collect(Collectors.toList());
		return employeeList;
	}
}

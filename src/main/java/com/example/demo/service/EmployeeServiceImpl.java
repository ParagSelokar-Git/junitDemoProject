package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Employee;
import com.example.demo.exceptions.EmployeeAlreadyPresent;
import com.example.demo.exceptions.EmployeeNotPresent;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public Employee addEmployee(Employee employee) {
		
		Employee employeePresentOrNot = employeeDao.getEmpById(employee.getId());
		if(employeePresentOrNot == null) {
			return employeeDao.addEmployee(employee);
		}else {
			throw new EmployeeAlreadyPresent();
		}
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeDao.getAllEmployees();
	}

	@Override
	public Employee getEmpById(Long empidL) {
		
		Employee employeePresentOrNot = employeeDao.getEmpById(empidL);
		if(employeePresentOrNot == null) {
			throw new EmployeeNotPresent("Please check the employee id");
		}else {
			return employeePresentOrNot;
		}
	}

	@Override
	public void deleteEmpById(Long empidL) {
	}

}

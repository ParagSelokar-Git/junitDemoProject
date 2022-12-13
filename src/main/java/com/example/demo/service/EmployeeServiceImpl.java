package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public Employee addEmployee(Employee employee) {
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeDao.getAllEmployees();
	}

	@Override
	public Employee getEmpById(Long empidL) {
		return employeeDao.getEmpById(empidL);
	}

	@Override
	public void deleteEmpById(Long empidL) {
	}

}

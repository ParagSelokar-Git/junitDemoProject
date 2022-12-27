package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmployeeNotPresent extends RuntimeException {
	private String message;

	public EmployeeNotPresent() {
	}

	public EmployeeNotPresent(String msg) {
		super(msg);
		this.message = msg;
	}

}

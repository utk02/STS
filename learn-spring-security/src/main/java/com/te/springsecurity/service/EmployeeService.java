package com.te.springsecurity.service;

import com.te.springsecurity.entity.dto.EmployeeDto;

public interface EmployeeService {
	public abstract EmployeeDto findByEmployeeId(String employeeID); 
}

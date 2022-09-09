package com.te.springsecurity.service.implementation;

import org.springframework.stereotype.Service;

import com.te.springsecurity.entity.dto.EmployeeDto;
import com.te.springsecurity.repository.EmployeeRepository;
import com.te.springsecurity.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeeServiceImplementation implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Override
	public EmployeeDto findByEmployeeId(String employeeID) {
		return null;
	}

}

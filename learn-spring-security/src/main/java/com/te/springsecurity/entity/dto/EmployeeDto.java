package com.te.springsecurity.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDto {
	private String employeeId;
	private String employeeName;
	private String employeeEmail;
}


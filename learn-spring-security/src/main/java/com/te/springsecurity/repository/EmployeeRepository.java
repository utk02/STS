package com.te.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.springsecurity.entity.Employee;
import com.te.springsecurity.entity.Mentor;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	Optional<Employee> findByEmployeeId(String employeeId);
}

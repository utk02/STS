package com.te.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.springsecurity.entity.Employee;
import com.te.springsecurity.entity.Mentor;
import com.te.springsecurity.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
	Optional<Role> findByRoleName(String roleName);
}

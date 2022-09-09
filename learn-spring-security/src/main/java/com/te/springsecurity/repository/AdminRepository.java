package com.te.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.springsecurity.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
	Optional<Admin> findByAdminId(String adminId);
}

package com.te.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.springsecurity.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
	Optional<AppUser> findByUsername(String username);
}

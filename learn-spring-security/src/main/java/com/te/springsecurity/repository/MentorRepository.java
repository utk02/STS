package com.te.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.springsecurity.entity.Mentor;

public interface MentorRepository extends JpaRepository<Mentor, String> {
	Optional<Mentor> findByMentorId(String mentorId);
}

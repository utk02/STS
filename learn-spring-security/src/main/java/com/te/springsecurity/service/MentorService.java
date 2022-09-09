package com.te.springsecurity.service;

import org.springframework.stereotype.Service;

import com.te.springsecurity.entity.dto.MentorDto;

@Service
public interface MentorService {
	public abstract MentorDto registerMentor(MentorDto mentorDto);
}

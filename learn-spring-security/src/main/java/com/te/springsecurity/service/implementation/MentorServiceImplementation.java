package com.te.springsecurity.service.implementation;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.springsecurity.entity.Mentor;
import com.te.springsecurity.entity.dto.MentorDto;
import com.te.springsecurity.repository.MentorRepository;
import com.te.springsecurity.service.MentorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MentorServiceImplementation implements MentorService {
	private final MentorRepository mentorRepository;
	@Override
	public MentorDto registerMentor(MentorDto mentorDto) {
		Mentor mentor = new Mentor();
		BeanUtils.copyProperties(mentorDto, mentor);
		mentorRepository.save(mentor);
		return mentorDto;
	}

}

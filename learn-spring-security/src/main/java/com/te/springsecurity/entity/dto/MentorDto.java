package com.te.springsecurity.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MentorDto {
	
	private String mentorId;
	private String mentorName;
	private String mentorEmail;
}

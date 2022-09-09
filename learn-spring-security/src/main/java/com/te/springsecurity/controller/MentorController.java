package com.te.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.springsecurity.response.GeneralResponse;

@RestController
@RequestMapping(path = "/api/v2")
public class MentorController {
	@GetMapping(path = "/mentor")
	public ResponseEntity<GeneralResponse> onlyAccessibleToMentor() {
		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for mentor"));
	}

}

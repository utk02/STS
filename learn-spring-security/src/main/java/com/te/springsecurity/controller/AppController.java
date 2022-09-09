package com.te.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.tools.sjavac.Log;
import com.te.springsecurity.entity.dto.LoginDto;
import com.te.springsecurity.entity.dto.MentorDto;
import com.te.springsecurity.jwt.utils.JwtUtils;
import com.te.springsecurity.response.GeneralResponse;
import com.te.springsecurity.service.MentorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
@Slf4j
public class AppController {
	private final JwtUtils jwtUtils;

	private final MentorService mentorService;

	private final AuthenticationManager authenticationManager;

	/* PUBLIC API */
	@GetMapping(path = "/check")
	public String checkSpring() {
		return "Spring Security Started";
	}

	/* PUBLIC API */
	@PostMapping(path = "/login")
	public ResponseEntity<GeneralResponse> login(@RequestBody LoginDto loginDto) {
		log.info("In controller method login(). Username passed {} and password {}", loginDto.getUsername(),
				loginDto.getPassword()); // to give log(printing statement while executing the programm)
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		String token = jwtUtils.generateToken(loginDto.getUsername());
		return ResponseEntity.ok().body(new GeneralResponse(token, HttpStatus.OK, null, null, "Login"));

	}

	/* ADMIN */
	@GetMapping(path = "/admin/{adminId}")
	public ResponseEntity<GeneralResponse> getAdminInfo(@PathVariable String adminId) {
		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for admin"));
	}

	/* ADMIN + MENTOR */
	@GetMapping(path = "/mentor/{mentorId}")
	public ResponseEntity<GeneralResponse> getMentorInfo(@PathVariable String mentorId) {
		return ResponseEntity.ok()
				.body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for admin and mentor"));
	}

	/* ADMIN + MENTOR + EMPLOYEE */
	@GetMapping(path = "/employee/{employeeId}")
	public ResponseEntity<GeneralResponse> getEmployeeInfo(@PathVariable String employeeId) {
		return ResponseEntity.ok()
				.body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for admin, mentor and employee"));
	}

	/* ADMIN */
	@GetMapping(path = "/admin")
	public ResponseEntity<GeneralResponse> onlyAccessibleToAdmin() {
		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for admin"));
	}

	/* ADMIN + MENTOR */
	@GetMapping(path = "/mentor")
	public ResponseEntity<GeneralResponse> onlyAccessibleToMentor() {
		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for mentor"));
	}

	/* ADMIN + MENTOR + EMPLOYEE */
	@GetMapping(path = "/employee")
	public ResponseEntity<GeneralResponse> onlyAccessibleToEmployee() {
		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for employee"));
	}

	@PostMapping(path = "/mentor")
	public ResponseEntity<GeneralResponse> registerMentor(@RequestBody MentorDto mentorDto) {
		mentorService.registerMentor(mentorDto);
		return ResponseEntity.ok().body(new GeneralResponse(null, HttpStatus.OK, null, null, "Only for Admin"));
	}

}

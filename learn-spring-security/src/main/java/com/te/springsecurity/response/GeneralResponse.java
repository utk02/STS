package com.te.springsecurity.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class GeneralResponse {
	private String token;
	private HttpStatus status;
	private String error;
	private String message;
	private LocalDateTime timestamp = LocalDateTime.now(); // here we don't use camel case
	private Object data;

	public GeneralResponse(String token, HttpStatus status, String error, String message, Object data) {
		super();
		this.token = token;
		this.status = status;
		this.error = error;
		this.message = message;
		this.data = data;
	}

}

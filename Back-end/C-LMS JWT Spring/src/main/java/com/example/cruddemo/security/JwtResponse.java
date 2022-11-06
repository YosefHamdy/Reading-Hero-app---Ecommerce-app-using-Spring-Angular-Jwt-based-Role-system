package com.example.cruddemo.security;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.Data;

@Data
@CrossOrigin
public class JwtResponse {

	private User user ;
	private String JwtToken;

	public JwtResponse(User user,String JwtToken) {
		this.JwtToken = JwtToken;
		this.user = user;
	}
	
	
}

package com.example.cruddemo.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Registration {

	private String username;
	private String password;
	
	public Registration(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
}

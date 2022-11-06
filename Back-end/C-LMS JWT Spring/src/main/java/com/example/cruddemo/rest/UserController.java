package com.example.cruddemo.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.security.User;
import com.example.cruddemo.security.UserService;

@RestController
@RequestMapping("/register")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

//	@GetMapping
//	public String registerForm() {
//		return "registration";
//	}

	@PostMapping
	public String processRegistration(@RequestBody User user) {
		userService.registerNewUser(user);
		return "User registered Sucessfully " +user;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/admin")
	public String processRegistrationAdmin(@RequestBody User user) {
		userService.registerNewAdmin(user);
		return "User registered Sucessfully " +user;
	}

}

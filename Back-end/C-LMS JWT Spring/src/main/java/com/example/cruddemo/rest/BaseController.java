package com.example.cruddemo.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.cruddemo.security.User;

public abstract class BaseController {

	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (User)authentication.getPrincipal();
	}
}

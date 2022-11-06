package com.example.cruddemo.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.cruddemo.entity.CustomerAssignedBook;
import com.example.cruddemo.security.User;

public interface CustomerAssignedBookService {

	public void save(CustomerAssignedBook theBook);
		
		 public User loadUserByUsername(String username)
				 throws UsernameNotFoundException;
	}



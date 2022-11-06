package com.example.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.security.User;


@Repository
public interface UserRepository	
		extends JpaRepository<User, Integer> {
	
		public User findByUsername(String username);


}

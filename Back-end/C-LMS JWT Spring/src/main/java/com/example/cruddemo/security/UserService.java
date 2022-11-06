package com.example.cruddemo.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cruddemo.dao.RoleRepository;
import com.example.cruddemo.dao.UserRepository;
import com.example.cruddemo.entity.Role;
import com.example.cruddemo.entity.RoleEnum;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepo;
	
	public User registerNewUser(User user) {
 		Role role = roleRepo.findByRoleName(RoleEnum.ROLE_USER);
 		Set<Role> roles = new HashSet<>();
 		roles.add(role);
 		user.setEnabled(1);
 		user.setRoleList(roles);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
 		return userRepository.save(user);
	}
	public User registerNewAdmin(User user) {
 		Role role = roleRepo.findByRoleName(RoleEnum.ROLE_ADMIN);
 		Set<Role> roles = new HashSet<>();
 		roles.add(role);
 		user.setEnabled(1);
 		user.setRoleList(roles);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
 		return userRepository.save(user);
	}
	
	public String getPasswordEncoded(String password) {
		return passwordEncoder.encode(password);
	}
}

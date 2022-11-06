package com.example.cruddemo.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cruddemo.dao.UserRepository;


@Service
public class UserInterfaceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	
	private AuthenticationManager authManager ;
	
	public UserInterfaceImpl(@Lazy AuthenticationManager authManager) {
		this.authManager = authManager;
	}


	public JwtResponse createJwtToken(SignInRequest signinRequest) throws Exception {
		String username = signinRequest.getUsername();
		String password = signinRequest.getPassword();
		authenticate(username,password);
		
		final UserDetails userDetails = loadUserByUsername(username);
		String newGeneratedToken= tokenUtil.generateToken(userDetails);
		User user = userRepository.findByUsername(username);
		return new JwtResponse(user, newGeneratedToken);
	
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("Invalid username or password.");
		else {
            return new org.springframework.security.core.userdetails.User(
            		user.getUsername(),
            		user.getPassword(),
            		getAuthorities(user));


			  }

	}
		
	 @SuppressWarnings("rawtypes")
	private Set getAuthorities(User user) {
		 Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		 
		    user.getRoleList().forEach(role -> {
	            authorities.add(new SimpleGrantedAuthority( role.getRoleName().name()));
	        });
		 
		 return authorities;
	 }
			
		
	  private void authenticate(String username, String password) throws Exception {
			try {
				authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); 
			}catch(DisabledException e) {
				throw new Exception("User is disabled");
			}catch(BadCredentialsException e) {
				throw new Exception("Bad Credentials was entered");
			}
				}

}

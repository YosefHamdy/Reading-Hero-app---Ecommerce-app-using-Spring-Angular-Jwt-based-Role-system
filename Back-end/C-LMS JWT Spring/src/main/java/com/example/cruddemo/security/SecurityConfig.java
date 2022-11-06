package com.example.cruddemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//	@Autowired	
//	private AuthFilter authFilter ;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthFilter authFilter() {
		return new AuthFilter();
	}
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		 return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable()
		 .authorizeRequests().antMatchers("/api/v1/auth", "/register/**").permitAll()
         .antMatchers(HttpHeaders.ALLOW).permitAll()
         .anyRequest().authenticated()
         .and()
         .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
         .and()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// STATELESS session prevent store any credentials in the session 
		
		http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
		

	}
	// encrypt password
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
//	private final String[] PUBLIC_ENDPOINTS = {
////			"/api/v1/auth/**",
////			"/api/books",
////			"/api/books/viewByCat/**"
//	};


	


	
	
}
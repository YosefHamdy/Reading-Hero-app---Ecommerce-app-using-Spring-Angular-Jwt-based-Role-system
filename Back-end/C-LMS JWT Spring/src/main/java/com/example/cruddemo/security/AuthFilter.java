package com.example.cruddemo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
/*
 * RESPONSIBLE FOR 
 * 1 - retrieve the token header<< from that retrieve bearer token
 * 2 - check from bearer token the username and jwt token and try to validate it "TokenUtil"
 * 3 - Once we retrieve the token and validated then it will just allow this particular request "filterChain".doFilter(request,response)*/
public class AuthFilter extends OncePerRequestFilter {

	@Value("${auth.header}")
	private String TOKEN_HEADER;
	@Autowired
	private UserInterfaceImpl userService ;
	@Autowired
	private TokenUtil tokenUtil ;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//Get token from Header that in request
		final String header = request.getHeader(TOKEN_HEADER);
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		//Check token valid or not
		
		if(header != null && securityContext.getAuthentication() == null) {
			// get Token without Bearer keyword by use substring method 
			String token = header.substring("Bearer ".length());
			String username = tokenUtil.getUserNameFromToken(token);
			if(username != null) {
				UserDetails userDetails = userService.loadUserByUsername(username);
				if(tokenUtil.isTokenValid(token,userDetails)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
							null ,
							userDetails.getAuthorities());
					// save the authorize
					authentication.setDetails(new WebAuthenticationDetailsSource()
							.buildDetails(request));
					SecurityContextHolder.getContext()
							.setAuthentication(authentication);
				} 
			}
		}
		
		filterChain.doFilter(request, response);
	}

}

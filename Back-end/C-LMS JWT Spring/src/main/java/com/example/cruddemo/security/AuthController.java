package com.example.cruddemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value ="/api/v1/auth")
@CrossOrigin

public class AuthController {

	
	@Autowired
	private UserInterfaceImpl userService;

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = {"","/"})// this mean user pass / or not at end of the url signin function will do it's work
	public JwtResponse signIn(@RequestBody SignInRequest signinRequest) throws Exception {
		return userService.createJwtToken(signinRequest);
				
	}
	
}

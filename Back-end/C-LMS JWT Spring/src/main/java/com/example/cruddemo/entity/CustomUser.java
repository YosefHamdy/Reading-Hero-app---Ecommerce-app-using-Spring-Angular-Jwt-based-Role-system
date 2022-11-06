package com.example.cruddemo.entity;
import java.util.Collection;

import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CustomUser extends User{
	  private static final long serialVersionUID = -3531439484732724601L;

	 
	  private final String username; 
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	public CustomUser(String username, String password, boolean enabled,
	         boolean accountNonExpired, boolean credentialsNonExpired,
	         boolean accountNonLocked,
	         Collection authorities) {

	             super(username, password, enabled, accountNonExpired,
	                credentialsNonExpired, accountNonLocked, authorities);
	             this.username = username;
	     }
	  
	}

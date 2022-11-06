package com.example.cruddemo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {

	private final String CLAIMS_SUBJECT ="sub";
	private final String CLAIMS_CREATED ="created";
	
	@Value("${auth.expiration}")
	// 7 days in seconds
	private Long TOKEN_VALIDITY = 604800L;
	@Value("${auth.secret}")
	private String TOKEN_SECRET;
	
	public String generateToken(UserDetails user) {
	/*	to generate token u need pass these : */
		//claims
		//expiration
		//sign
		//compact : "mean that convert claims,expiration,sign to String" then return it 
		
		Map<String,Object> claims = new HashMap<>();
		claims.put(CLAIMS_SUBJECT, user.getUsername());
		claims.put(CLAIMS_CREATED, new Date());
		// builder to build the token "Generate"
		return Jwts.builder()
/*claims*/		        .setClaims(claims)
/*expiration*/			.setExpiration(generateExpirationDate())
/*sign*/			    .signWith(SignatureAlgorithm.HS512,TOKEN_SECRET)
/*compact*/			    .compact();
	}

	public String getUserNameFromToken(String token) {
		try {
			// parser to read the token
			Claims claims = getClaims(token);
			// get username from token
			return claims.getSubject();
		} catch (Exception e) {
			return null;
		}
		
	}

	
	
	private Date generateExpirationDate() {
		// convert second to milli second
		return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000 );
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = getUserNameFromToken(token);
		return ( username.equals(userDetails.getUsername()) && (!isTokenExpired(token)));
	}

	private boolean isTokenExpired(String token) {
		Date expiration = getClaims(token).getExpiration();
		return expiration.before(new Date());
	}
	private Claims getClaims(String token) {
		Claims claims;
	 try {
			// parser to read the token

		 	claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
				.parseClaimsJws(token)
				.getBody();
	 }catch (Exception e) {
		 	claims = null ;
	 }
	 return claims ;
	 }
}

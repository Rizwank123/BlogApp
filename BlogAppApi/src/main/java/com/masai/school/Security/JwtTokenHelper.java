package com.masai.school.Security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {
	public static final long JWT_TOKEN_VALIDITY=5*60*60;
	private String secret="findme";
	
	
	//get user from jwt token 
	
	public String getUserFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	//token expiration date
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token,Function<Claims,T> claimsResolver) {
		final Claims claims=getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	//retrive any information from token we will need secret key
	
	public Claims getAllClaimsFromToken(String torken) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJwt(torken).getBody();
	}
	
	//check if token has expired 
	
	private boolean isTokenExpired(String token) {
		final Date expiration=getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	//generate token 
	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims=new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
	}
	
	
	
	
	public String doGenerateToken(Map<String,Object>claims,String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new java.util.Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*100))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	//validate token
	public boolean validateToken(String token,UserDetails userDetails) {
		
		final String usename=getUserFromToken(token);
		return (usename.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}

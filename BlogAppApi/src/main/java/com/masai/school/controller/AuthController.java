package com.masai.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.school.Security.JwtTokenHelper;
import com.masai.school.payload.JwtAuthRequest;
import com.masai.school.payload.JwtAuthResponse;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtRequest) throws Exception {
		
		this.authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		UserDetails userDetails=	this.userDetailService.loadUserByUsername(jwtRequest.getUsername());
		
		String token=this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		
	}
	
	private void authenticate(String email,String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(email, password);
		
try {
	this.authenticationManager.authenticate(authenticationToken);

}catch(BadCredentialsException ex) {
	//System.out.println("Invalid username Or Password !!");
	throw new BadCredentialsException("Invalid Username or Password");
}
		
	}

}

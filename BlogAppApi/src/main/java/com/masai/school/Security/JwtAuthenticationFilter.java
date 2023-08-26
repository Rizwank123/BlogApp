package com.masai.school.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter{

	@Autowired
	private  UserDetailsService userDetailService;
	@Autowired
	
	private JwtTokenHelper jwtHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requstToken=request.getHeader("Authorization");
	
		//System.out.println(requstToken);
		String username=null;
		String token=null;
		if(requstToken !=null && requstToken.startsWith("Bearer")) {
		 token=requstToken.substring(7);
		 try {
			 username=this.jwtHelper.getUserFromToken(token);
		 }catch(IllegalArgumentException ex) {
			 System.out.println("unable to get jwt token");
		 }
		 catch(ExpiredJwtException e) {
			 System.out.println("Token has Expired");
		 } catch(MalformedJwtException e) {
			 System.out.println("Invalid jwt token");
		 }
		}
		else {
			System.out.println("Jwt token does not begin with Bearer");
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails=userDetailService.loadUserByUsername(username);
			if(jwtHelper.validateToken(token, userDetails)) {
				
				//make auth 
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken( userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}else {
				System.out.println("invalid jwt token");
			}
			
		}else {
			System.out.println("username is null or context is null");
			
			
		}
		filterChain.doFilter(request, response);
		
	}

}

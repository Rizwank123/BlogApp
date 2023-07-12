package com.masai.school.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.school.Repositroy.UserRepo;
import com.masai.school.entities.User;
import com.masai.school.exception.ResourceNotFoundException;

@Service
public class CustomUserDetailService  implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//load user from data 
		User user=userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "email"+username, 0));
		
		
		return user ;
	}

}

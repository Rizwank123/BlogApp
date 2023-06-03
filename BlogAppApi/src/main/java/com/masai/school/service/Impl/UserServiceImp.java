package com.masai.school.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.school.Repositroy.UserRepo;
import com.masai.school.entities.User;
import com.masai.school.exception.ResourceNotFoundException;
import com.masai.school.payload.UserDto;
import com.masai.school.service.UserService;
@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		User usr=this.dtoToUser(user);
		User savedUser=userRepo.save(usr);
		
		
		return userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		// TODO Auto-generated method stub
		User usr=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		usr.setName(user.getName());
		usr.setEmail(user.getEmail());
		usr.setAbout(user.getAbout());
		usr.setPassword(user.getPassword());
		User updateUser=userRepo.save(usr);
		
		return userToUserDto(updateUser) ;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User usr=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		return userToUserDto(usr);
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		
		List<User> users=userRepo.findAll();
		List<UserDto> list=users.stream().map(user->userToUserDto(user)).collect(Collectors.toList());
		return list;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		userRepo.delete(user);
		
	}
	private User dtoToUser(UserDto userDto) {
		
		User user=new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		return user;
	}
	public UserDto userToUserDto(User user) {
		UserDto udt=new UserDto();
		udt.setId(user.getId());
		udt.setName(user.getName());
		udt.setEmail(user.getEmail());
		udt.setPassword(user.getPassword());
		udt.setAbout(user.getAbout());
		return udt;
	}

}

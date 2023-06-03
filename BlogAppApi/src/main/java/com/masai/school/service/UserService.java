package com.masai.school.service;

import java.util.List;

import com.masai.school.payload.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUser();
	void deleteUser(Integer userId);
	

}

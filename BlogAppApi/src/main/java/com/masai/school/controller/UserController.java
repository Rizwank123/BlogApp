package com.masai.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.school.payload.ApiResponse;
import com.masai.school.payload.UserDto;
import com.masai.school.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	//post -create user 
	//put- update user 
	// delete delete user
	//Get user get
	@Autowired
	
	private UserService userService;
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
		UserDto createUser=userService.createUser(user);
		return new ResponseEntity<>(createUser,HttpStatus.CREATED);
	}
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId ){
		
		UserDto updateUser=userService.updateUser(userDto, userId);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
		UserDto user=userService.getUserById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user Deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUser());
	}
	
}

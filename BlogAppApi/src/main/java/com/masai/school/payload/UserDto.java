package com.masai.school.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
	 
	private Integer id;
	@NotEmpty
	@Size(min=4,max=20,message="Username must be min of 4 character !!")
	private String name;
	@Email(message="email address not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message="Password must be min of 3 character and max of 10 character !")
	private  String password;
	@NotEmpty
	private String about;
}

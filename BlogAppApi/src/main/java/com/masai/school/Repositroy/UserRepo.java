package com.masai.school.Repositroy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.school.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);

}

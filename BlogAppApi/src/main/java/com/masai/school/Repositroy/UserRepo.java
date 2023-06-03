package com.masai.school.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.school.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}

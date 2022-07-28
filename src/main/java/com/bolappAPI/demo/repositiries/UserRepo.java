package com.bolappAPI.demo.repositiries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolappAPI.demo.models.User;

public interface UserRepo extends  JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);

}

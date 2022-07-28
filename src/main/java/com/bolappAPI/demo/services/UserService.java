package com.bolappAPI.demo.services;

import java.util.List;

import com.bolappAPI.demo.models.User;
import com.bolappAPI.demo.payloads.UserDTO;

public interface UserService {
	UserDTO CreateUser(UserDTO user); //DTO = Data Transfer Object
	UserDTO updateUser(UserDTO user,Integer userID); //DTO = Data Transfer Object
	UserDTO getUserByID(Integer userID); //DTO = Data Transfer Object
	List<UserDTO> getAllUser(); //DTO = Data Transfer Object
	void deleteUser(Integer userID); //DTO = Data Transfer Object

}

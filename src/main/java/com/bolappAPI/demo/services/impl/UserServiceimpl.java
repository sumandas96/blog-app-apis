package com.bolappAPI.demo.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolappAPI.demo.exceptions.ResourceNotFoundException;
import com.bolappAPI.demo.models.User;
import com.bolappAPI.demo.payloads.UserDTO;
import com.bolappAPI.demo.repositiries.UserRepo;
import com.bolappAPI.demo.services.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO CreateUser(UserDTO userdto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUSer(userdto);
		User saveuser = userRepo.save(user);
		
		return this.userToDTO(saveuser);
	}

	@Override
	public UserDTO updateUser(UserDTO userdto, Integer userID) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userID)
				.orElseThrow(()-> new ResourceNotFoundException("user","id",userID));
		
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setAbout(userdto.getAbout());
		user.setPassword(userdto.getPassword());
		
		User updatedUSer = this.userRepo.save(user);
		UserDTO userDTO1 = this.userToDTO(updatedUSer);
		return userDTO1;
	}

	@Override
	public UserDTO getUserByID(Integer userID) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userID)
				.orElseThrow(()-> new ResourceNotFoundException("user","id",userID));
		return this.userToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		// TODO Auto-generated method stub
		List<User> userlist = userRepo.findAll();
		List<UserDTO> userdtos = userlist.stream().map(user -> this.userToDTO(user)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userID) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userID)
				.orElseThrow(()-> new ResourceNotFoundException("user","id",userID));
		this.userRepo.delete(user);

	}

	private User dtoToUSer(UserDTO userdto) {
		
		User user = this.modelMapper.map(userdto, User.class);
		
//		User user = new User();
//		user.setId(userdto.getId());
//		user.setName(userdto.getName());
//		user.setEmail(userdto.getEmail());
//		user.setAbout(userdto.getAbout());
//		user.setPassword(userdto.getPassword());

		return user;

	}
	
	private UserDTO userToDTO(User user) {
		
		UserDTO userdto = this.modelMapper.map(user, UserDTO.class);
		
//		UserDTO userdto = new UserDTO();
//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setAbout(user.getAbout());
//		userdto.setPassword(user.getPassword());

		return userdto;

	}

}

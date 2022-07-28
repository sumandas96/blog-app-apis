package com.bolappAPI.demo.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.bolappAPI.demo.payloads.ApiResponse;
import com.bolappAPI.demo.payloads.UserDTO;
import com.bolappAPI.demo.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	
	@Autowired
	private UserService userService;

	// post
	@PostMapping("/")
	public ResponseEntity<UserDTO> createuser(@Valid @RequestBody UserDTO useeDto) {
		UserDTO createUser = this.userService.CreateUser(useeDto);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);

	}

	@PutMapping("/{userID}")
	public ResponseEntity<UserDTO> updateuser(@Valid @RequestBody UserDTO userDto, @PathVariable("userID") Integer uid) {
		UserDTO updateUser = this.userService.updateUser(userDto, uid);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);

	}

	@DeleteMapping("/{userID}")
	public ResponseEntity<?> deleteuser(@PathVariable("userID") Integer uid) {
		this.userService.deleteUser(uid);
		
		//return new ResponseEntity(Map.of("message", "User Deleted Successfully"), HttpStatus.OK);
		
		return new ResponseEntity(new ApiResponse("User Deleted Successfully",true), HttpStatus.OK);

	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	@GetMapping("/{userID}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("userID") Integer uid){
		return ResponseEntity.ok(this.userService.getUserByID(uid));
	}


}

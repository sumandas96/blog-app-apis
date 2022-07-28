//package com.bolappAPI.demo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.bolappAPI.demo.exceptions.ResourceNotFoundException;
//import com.bolappAPI.demo.models.User;
//import com.bolappAPI.demo.repositiries.UserRepo;
//
//@Service
//public class CustomUserDetailsService  implements UserDetailsService{
//	
//	
//	
//	@Autowired
//	private UserRepo userRepo;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		
//		//loading user from database by username
//		
//		User user = this.userRepo.findByEmail(username).
//		orElseThrow(()->new ResourceNotFoundException("UserName", "Email:" +username,0));
//		
//		
//		
//		return user;
//	}
//
//}

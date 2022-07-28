//package com.bolappAPI.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bolappAPI.demo.payloads.JwtAuthRequest;
//import com.bolappAPI.demo.payloads.JwtAuthResponse;
//import com.bolappAPI.demo.security.JwtTokenHelper;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthController {
//	
//	@Autowired
//	private JwtTokenHelper jwtHelper;
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	@Autowired
//	private AuthenticationManager manager;
//	
//	@PostMapping("/login")
//	public ResponseEntity<JwtAuthResponse> createTpken(@RequestBody JwtAuthRequest
//			request){
//		this.authenticate(request.getUsername(),request.getPassword());
//		UserDetails userdetails = this.userDetailsService.loadUserByUsername(request.getUsername());
//		String generateToekn = this.jwtHelper.generateToekn(userdetails);
//		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
//		jwtAuthResponse.setToken(generateToekn);
//		System.out.println(jwtAuthResponse);
//		
//		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse,HttpStatus.OK);
//		
//		
//	}
//
//	private void authenticate(String username, String password) {
//		// TODO Auto-generated method stub
//		
//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//		
//		this.manager.authenticate(authenticationToken);
//	
//	
//	}
//
//}

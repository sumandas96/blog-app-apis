//package com.bolappAPI.demo.security;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.SignatureException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//	@Autowired
//	private UserDetailsService userDetailservice;
//
//	@Autowired
//	private JwtTokenHelper jwtHelper;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//
//		// 1.get token
//
//		String requestToken = request.getHeader("Authorization");
//
//		System.out.println("Reqst token is " +requestToken);
//
//		String username = null;
//		String token = null;
//
//		if (request != null && requestToken.startsWith("Bearer")) {
//
//			token = requestToken.substring(7);
//
//			try {
//				username = this.jwtHelper.getUsernameFromToken(token);
//			} catch (IllegalArgumentException e) {
//				System.out.println("Unable to get Jwt Token");
//			}
//
//			catch (ExpiredJwtException e) {
//				System.out.println(" Jwt Token expired");
//			} catch (MalformedJwtException e) {
//				System.out.println(" invalid Jwt ");
//			}
//
//		}
//		else {
//			System.out.println("Jwt token does not begin with Bearer");
//		}
//
//		// once we get the token ,now validate
//
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//			UserDetails loadUserByUsername = this.userDetailservice.loadUserByUsername(username);
//
//			if (this.jwtHelper.validateToken(token, loadUserByUsername)) {
//
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//						loadUserByUsername, null, loadUserByUsername.getAuthorities());
//
//				usernamePasswordAuthenticationToken
//						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//			} else {
//				System.out.println("Invalid jwt token");
//
//			}
//
//		} else {
//			System.out.println("Username or context is null");
//
//		}
//		
//		filterChain.doFilter(request, response);
//
//	}
//
//}

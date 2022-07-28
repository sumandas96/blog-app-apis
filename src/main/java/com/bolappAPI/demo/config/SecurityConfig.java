//package com.bolappAPI.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.bolappAPI.demo.security.CustomUserDetailsService;
//import com.bolappAPI.demo.security.JwtAuthenticationEntryPoint;
//import com.bolappAPI.demo.security.JwtAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private CustomUserDetailsService userDetailsService;
//
//	@Autowired
//	private JwtAuthenticationEntryPoint authenticationEntryPoint;
//
//	@Autowired
//	private JwtAuthenticationFilter authenticationFilter;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub
//
////		http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
//
//		http.csrf().disable().authorizeHttpRequests().antMatchers("api/v1/auth/login")
//		.permitAll().anyRequest().authenticated().and().exceptionHandling()
//				.authenticationEntryPoint(this.authenticationEntryPoint).and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		http.addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//	}
//
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		// TODO Auto-generated method stub
//		return super.authenticationManagerBean();
//	}
//
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//}

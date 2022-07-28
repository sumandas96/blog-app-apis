package com.bolappAPI.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bolappAPI.demo.repositiries.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private UserRepo userRepo;
	
	@Test
	void contextLoads() {
		
	}
	@Test
	public void testRepo() {
		String name = userRepo.getClass().getName();
		String packagename = userRepo.getClass().getPackageName();
		System.out.println(name);
		System.out.println(packagename);
	}

}

package com.bolappAPI.demo;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bolappAPI.demo.controller.UserController;
import com.bolappAPI.demo.models.User;
import com.bolappAPI.demo.payloads.UserDTO;
import com.bolappAPI.demo.repositiries.UserRepo;
import com.bolappAPI.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	MockMvc mockmvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	UserService userRepo;
	
	UserDTO records; 
	
	@BeforeEach
	public void setUp() {
		records  = UserDTO.builder()
				.id(3)
				.name("Suman Das")
				.email("codersuman96@gmail.com")
				.about("For Testing")
				.password("testingdemo")
				.build();
	}

	UserDTO RECORD_1 = new UserDTO(12, "Test1", "abc@.com", "testingdemo1", "For testing");
	UserDTO RECORD_2 = new UserDTO(13, "Test2", "c@.com", "testingdemo2", "For testing");
	UserDTO RECORD_3 = new UserDTO(14, "Test3", "d@.com", "testingdemo3", "For testing");

	@Test
	public void getAllValueTest() throws Exception {

		List<UserDTO> allUsers = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

		when(userRepo.getAllUser()).thenReturn(allUsers);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/").accept(MediaType.APPLICATION_JSON);

		mockmvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
//			.andExpect(content().json("{\"id\": 5,\"name\": \"sky\",\"email\": \"s.com\", \"password\": \"$2a$10$hHnPCwrpJTQPF.C.COgDTOkv6A4TWHSi0HmjY7azl.73FBEYxYgPa\",\"about\": \"I am a fucker\""))

				.andReturn();
	}
	
	
	
	@Test
	@Timeout( 1)
	public void getUserByID_Test() throws Exception {

		

		//when(userRepo.getUserByID(RECORD_1.getId())).thenReturn(java.util.Optional.of(RECORD_1));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/12").accept(MediaType.APPLICATION_JSON);

		mockmvc.perform(requestBuilder).andExpect(status().isOk())
		//.andExpect(jsonPath("$[0].name").value("Test1"))
		.andReturn();
//			.andExpect(content().json("{\"id\": 5,\"name\": \"sky\",\"email\": \"s.com\", \"password\": \"$2a$10$hHnPCwrpJTQPF.C.COgDTOkv6A4TWHSi0HmjY7azl.73FBEYxYgPa\",\"about\": \"I am a fucker\""))

				
	}
	
	
	@Test
	public void createRecord_success() throws Exception {
		
		UserDTO records = UserDTO.builder()
				.id(3)
				.name("Suman Das")
				.email("codersuman96@gmail.com")
				.about("For Testing")
				.password("testingdemo")
				.build();
		
		
		when(userRepo.CreateUser(records)).thenReturn(records);

	RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(records));

		mockmvc.perform(requestBuilder).andExpect(status().isCreated());
	}
	
	
	@Test
	public void updateRecord_success() throws Exception {
		
		UserDTO records = UserDTO.builder()
				.id(3)
				.name("S Das")
				.email("codersuman96@gmail.com")
				.about("For Testing")
				.password("testingdemo")
				.build();
		when(userRepo.getUserByID(RECORD_3.getId())).thenReturn(RECORD_3);
		when(userRepo.CreateUser(records)).thenReturn(records);
		

	RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/users/12")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(records));

		mockmvc.perform(requestBuilder).andExpect(status().isOk());
	}
	
	@Test
	public void deleteuserById_success() throws Exception {
	    when(userRepo.getUserByID(RECORD_2.getId())).thenReturn(RECORD_2);

	    mockmvc.perform(MockMvcRequestBuilders
	            .delete("/api/users/12")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}


}

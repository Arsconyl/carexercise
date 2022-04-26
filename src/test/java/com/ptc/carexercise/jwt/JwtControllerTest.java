package com.ptc.carexercise.jwt;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class JwtControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void getToken() throws Exception {
		mockMvc.perform(post("/login")
						.contentType("application/json")
						.content("{\"username\":\"carexercise\",\"password\":\"carexercise\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.jwtToken").exists());
	}

	@Test
	@Order(2)
	void getTokenWithInvalidCredentials() throws Exception {
		mockMvc.perform(post("/login")
						.contentType("application/json")
						.content("{\"username\":\"carexercise\",\"password\":\"invalid\"}"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@Order(3)
	void getTokenWithInvalidUsername() throws Exception {
		mockMvc.perform(post("/login")
						.contentType("application/json")
						.content("{\"username\":\"invalid\",\"password\":\"carexercise\"}"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@Order(4)
	void getTokenWithInvalidPassword() throws Exception {
		mockMvc.perform(post("/login")
						.contentType("application/json")
						.content("{\"username\":\"carexercise\",\"password\":\"invalid\"}"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@Order(5)
	void getTokenWithInvalidUsernameAndPassword() throws Exception {
		mockMvc.perform(post("/login")
						.contentType("application/json")
						.content("{\"username\":\"invalid\",\"password\":\"invalid\"}"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@Order(6)
	void getTokenWithInvalidUsernameAndPasswordAndEmptyUsername() throws Exception {
		mockMvc.perform(post("/login")
						.contentType("application/json")
						.content("{\"username\":\"\",\"password\":\"invalid\"}"))
				.andExpect(status().isUnauthorized());
	}
}

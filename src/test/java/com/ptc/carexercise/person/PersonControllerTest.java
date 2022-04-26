package com.ptc.carexercise.person;

import com.ptc.carexercise.jwt.JwtUserDetailsService;
import com.ptc.carexercise.jwt.TokenManager;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Test
	@Order(1)
	void testGetPeople() throws Exception {
		mockMvc.perform(
			get("/people")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	void testGetPerson() throws Exception {
		mockMvc.perform(
			get("/people/1")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.firstName").value("John"))
			.andExpect(jsonPath("$.lastName").value("Doe"))
			.andExpect(jsonPath("$.company").value("Acme"))
			.andExpect(jsonPath("$.type").value("PHYSICAL"));
	}

	@Test
	@Order(3)
	void testGetPersonNotFound() throws Exception {
		mockMvc.perform(
			get("/people/0")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isNotFound());
	}

	@Test
	@Order(4)
	void testGetPersonBadRequest() throws Exception {
		mockMvc.perform(
			get("/people/abc")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isBadRequest());
	}

	@Test
	@Order(5)
	void testDeletePerson() throws Exception {
		mockMvc.perform(
			delete("/people/10")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isOk());
		mockMvc.perform(
			delete("/people/10")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("application/json"));
		mockMvc.perform(
			get("/people/10")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isNotFound());
	}

	@Test
	@Order(6)
	void testAddPerson() throws Exception {
		mockMvc.perform(
			post("/people")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise")))
			.contentType("application/json")
			.content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"company\":\"Acme\",\"type\":\"PHYSICAL\"}"))
			.andExpect(status().isCreated())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.id").value(12L))
			.andExpect(jsonPath("$.firstName").value("John"))
			.andExpect(jsonPath("$.lastName").value("Doe"))
			.andExpect(jsonPath("$.company").value("Acme"))
			.andExpect(jsonPath("$.type").value("PHYSICAL"));
	}

	@Test
	@Order(7)
	void testUpdatePerson() throws Exception {
		mockMvc.perform(
			put("/people/7")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise")))
			.contentType("application/json")
			.content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"company\":\"Acme\",\"type\":\"PHYSICAL\"}"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.id").value(7L))
			.andExpect(jsonPath("$.firstName").value("John"))
			.andExpect(jsonPath("$.lastName").value("Doe"))
			.andExpect(jsonPath("$.company").value("Acme"))
			.andExpect(jsonPath("$.type").value("PHYSICAL"));
	}

	@Test
	@Order(8)
	void testGetPeopleWithoutToken() throws Exception {
		mockMvc.perform(
			get("/people")
				.header("Authorization",
						"Bearer "))
			.andExpect(status().isUnauthorized());
	}
}
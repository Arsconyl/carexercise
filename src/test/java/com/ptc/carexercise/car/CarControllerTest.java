package com.ptc.carexercise.car;

import com.ptc.carexercise.jwt.JwtUserDetailsService;
import com.ptc.carexercise.jwt.TokenManager;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Test
	@Order(1)
	void testGetCars() throws Exception {
		mockMvc.perform(
			get("/api/cars")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	void testGetCar() throws Exception {
		mockMvc.perform(
			get("/api/cars/1")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.brand").value("Mercedes"))
			.andExpect(jsonPath("$.numberOfSeats").value(5L))
			.andExpect(jsonPath("$.color").value("RED"));
	}

	@Test
	@Order(3)
	void testGetCarNotFound() throws Exception {
		mockMvc.perform(
			get("/api/cars/0")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isNotFound());
	}

	@Test
	@Order(4)
	void testGetCarBadRequest() throws Exception {
		mockMvc.perform(
			get("/api/cars/abc")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isBadRequest());
	}

	@Test
	@Order(5)
	void testDeleteCar() throws Exception {
		mockMvc.perform(
			delete("/api/cars/10")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isOk());
		mockMvc.perform(
			delete("/api/cars/10")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType("application/json"));
		mockMvc.perform(
			get("/api/cars/10")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise"))))
			.andExpect(status().isNotFound());
	}

	@Test
	@Order(6)
	void testAddCar() throws Exception {
		mockMvc.perform(
			post("/api/cars")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise")))
			.contentType("application/json")
			.content("{\"brand\":\"Mercedes\",\"numberOfSeats\":5,\"color\":\"RED\"}"))
			.andExpect(status().isCreated())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.id").value(30))
			.andExpect(jsonPath("$.brand").value("Mercedes"))
			.andExpect(jsonPath("$.numberOfSeats").value(5L))
			.andExpect(jsonPath("$.color").value("RED"));

	}

	@Test
	@Order(7)
	void testUpdateCar() throws Exception {
		mockMvc.perform(
			put("/api/cars/16")
			.header("Authorization",
					"Bearer " + tokenManager.generateToken(userDetailsService.loadUserByUsername("carexercise")))
			.contentType("application/json")
			.content("{\"brand\":\"Mercedes\",\"numberOfSeats\":5,\"color\":\"RED\"}"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.id").value(16))
			.andExpect(jsonPath("$.brand").value("Mercedes"))
			.andExpect(jsonPath("$.numberOfSeats").value(5L))
			.andExpect(jsonPath("$.color").value("RED"));
	}

	@Test
	@Order(8)
	void testGetCarsWithoutToken() throws Exception {
		mockMvc.perform(
			get("/api/cars")
			.header("Authorization",
					"Bearer "))
			.andExpect(status().isUnauthorized());
	}
}
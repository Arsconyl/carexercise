package com.ptc.carexercise.car;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void contextLoads() {
	}

	@Test
	@Order(2)
	void testGetCars() throws Exception {
		mockMvc.perform(get("/cars")).andExpect(status().isOk());
	}

	@Test
	@Order(3)
	void testGetCar() throws Exception {
		mockMvc.perform(get("/cars/1")).andExpect(status().isOk());
	}

	@Test
	@Order(4)
	void testGetCarNotFound() throws Exception {
		mockMvc.perform(get("/cars/0")).andExpect(status().isNotFound());
	}

	@Test
	@Order(5)
	void testGetCarBadRequest() throws Exception {
		mockMvc.perform(get("/cars/abc")).andExpect(status().isBadRequest());
	}

	@Test
	@Order(6)
	void testDeleteCar() throws Exception {
		mockMvc.perform(delete("/cars/2")).andExpect(status().isOk());
	}
}
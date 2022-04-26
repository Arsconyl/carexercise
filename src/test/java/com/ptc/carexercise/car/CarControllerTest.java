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
	void testGetCars() throws Exception {
		mockMvc.perform(get("/cars")).andExpect(status().isOk());
	}

	@Test
	@Order(2)
	void testGetCar() throws Exception {
		mockMvc.perform(
				get("/cars/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.brand").value("Mercedes"))
				.andExpect(jsonPath("$.numberOfSeats").value(5L))
				.andExpect(jsonPath("$.color").value("RED"));
	}

	@Test
	@Order(3)
	void testGetCarNotFound() throws Exception {
		mockMvc.perform(get("/cars/0")).andExpect(status().isNotFound());
	}

	@Test
	@Order(4)
	void testGetCarBadRequest() throws Exception {
		mockMvc.perform(get("/cars/abc")).andExpect(status().isBadRequest());
	}

	@Test
	@Order(5)
	void testDeleteCar() throws Exception {
		mockMvc.perform(
				delete("/cars/10"))
				.andExpect(status().isOk());
		mockMvc.perform(
				delete("/cars/10"))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType("application/json"));
		mockMvc.perform(
				get("/cars/10"))
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(6)
	void testAddCar() throws Exception {
		mockMvc.perform(
				post("/cars")
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
				put("/cars/16")
				.contentType("application/json")
				.content("{\"brand\":\"Mercedes\",\"numberOfSeats\":5,\"color\":\"RED\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value(16))
				.andExpect(jsonPath("$.brand").value("Mercedes"))
				.andExpect(jsonPath("$.numberOfSeats").value(5L))
				.andExpect(jsonPath("$.color").value("RED"));
	}
}
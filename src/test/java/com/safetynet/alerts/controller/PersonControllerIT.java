package com.safetynet.alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Tests Person controller with simulated web requests
 * 
 * @see PersonController
 * 
 * @author MikeMatthews
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIT {

	@Autowired
	MockMvc mockMvc;

	/**
	 * Tests Post request
	 * 
	 * @see PersonController#savePerson(Person)
	 */
	@Test
	public void testPostPerson() throws Exception {

		mockMvc.perform(post("/person")).andExpect(status().isOk());
	}

	/**
	 * Tests Put request
	 * 
	 * @see PersonController#updatePerson(Person)
	 */
	@Test
	public void testPutPerson() throws Exception {

		mockMvc.perform(put("/person")).andExpect(status().isOk());
	}

	/**
	 * Tests Delete request
	 * 
	 * @see PersonController#deletePerson(Person)
	 */
	@Test
	public void testDeletePerson() throws Exception {

		mockMvc.perform(delete("/person")).andExpect(status().isOk());
	}
}
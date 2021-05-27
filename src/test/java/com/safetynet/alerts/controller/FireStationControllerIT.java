package com.safetynet.alerts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.FireStation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Tests FireStation controller with simulated web requests
 * 
 * @see FireStationController
 * 
 * @author Mike Matthews
 */
@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerIT {

	@Autowired
	MockMvc mockMvc;

	TestDataGenerator dataGenerator = new TestDataGenerator();
	ObjectMapper jsonMapper = new ObjectMapper();

	/**
	 * Tests Post request with valid object
	 * 
	 * @see FireStationController#saveStation(FireStation)
	 */
	@Test
	public void testPostValidStation() throws Exception {

		// GIVEN
		FireStation testData = dataGenerator.generateTestStation();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// WHEN
		MvcResult result = mockMvc.perform(post("/firestation").contentType("application/json").content(jsonString))
				.andExpect(status().isOk()).andReturn();

		// THEN
		String stringResult = result.getResponse().getContentAsString();
		FireStation responseStation = jsonMapper.readValue(stringResult, FireStation.class);

		// replaces index because it should be updated
		if (testData.getId() != responseStation.getId())
			testData.setId(responseStation.getId());

		if (!testData.getAddress().equals(responseStation.getAddress())
				|| !testData.getStationId().equals(responseStation.getStationId()))
			fail("Expected test data and result to be the same but they were not");
	}

	/**
	 * Tests Post request with invalid object
	 * 
	 * @see FireStationController#saveStation(FireStation)
	 */
	@Test
	public void testPostInvalidStation() throws Exception {

		// GIVEN
		FireStation testData = dataGenerator.generateTestStation();
		testData.setAddress(null);
		String jsonString = jsonMapper.writeValueAsString(testData);

		// WHEN
		mockMvc.perform(post("/firestation").contentType("application/json").content(jsonString))
				.andExpect(status().isNotAcceptable());
	}

	/**
	 * Tests Put request with valid object
	 * 
	 * @see FireStationController#updateStation(FireStation)
	 */
	@Test
	public void testPutValidStation() throws Exception {

		// GIVEN
		String expectedAddress = "new test address";
		FireStation testData = dataGenerator.generateTestStation();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// save initial object
		MvcResult initResult = mockMvc.perform(post("/firestation").contentType("application/json").content(jsonString))
				.andReturn();

		String resultString = initResult.getResponse().getContentAsString();
		testData = jsonMapper.readValue(resultString, FireStation.class);
		testData.setAddress(expectedAddress);
		jsonString = jsonMapper.writeValueAsString(testData);

		// WHEN
		MvcResult result = mockMvc.perform(put("/firestation").contentType("application/json").content(jsonString))
				.andExpect(status().isOk()).andReturn();

		// THEN
		String stringResult = result.getResponse().getContentAsString();
		FireStation responseStation = jsonMapper.readValue(stringResult, FireStation.class);

		assertEquals(expectedAddress, responseStation.getAddress());
	}

	/**
	 * Tests Put request with invalid object
	 * 
	 * @see FireStationController#updateStation(FireStation)
	 */
	@Test
	public void testPutInvalidStation() throws Exception {

		// GIVEN
		String expectedAddress = null;
		FireStation testData = dataGenerator.generateTestStation();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// save initial object
		MvcResult initResult = mockMvc.perform(post("/firestation").contentType("application/json").content(jsonString))
				.andReturn();

		String stringResult = initResult.getResponse().getContentAsString();
		testData = jsonMapper.readValue(stringResult, FireStation.class);
		testData.setAddress(expectedAddress);
		jsonString = jsonMapper.writeValueAsString(testData);

		// WHEN
		mockMvc.perform(put("/firestation").contentType("application/json").content(jsonString))
				.andExpect(status().isNotAcceptable());
	}

	/**
	 * Tests Delete request with valid object
	 * 
	 * @see FireStationController#deleteStation(FireStation)
	 */
	@Test
	public void testDeleteValidStation() throws Exception {

		// GIVEN
		FireStation testData = dataGenerator.generateTestStation();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// save initial object
		MvcResult initResult = mockMvc.perform(post("/firestation").contentType("application/json").content(jsonString))
				.andExpect(status().isOk()).andReturn();

		String stringResponse = initResult.getResponse().getContentAsString();
		testData = jsonMapper.readValue(stringResponse, FireStation.class);

		// WHEN
		mockMvc.perform(delete("/firestation").contentType("application/json")
				.content(jsonMapper.writeValueAsString(testData.getId()))).andExpect(status().isOk());
	}

	/**
	 * Tests Delete request with invalid object
	 * 
	 * @see FireStationController#deleteStation(FireStation)
	 */
	@Test
	public void testDeleteInvalidStation() throws Exception {

		// GIVEN
		FireStation testData = dataGenerator.generateTestStation();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// save initial object
		mockMvc.perform(post("/firestation").contentType("application/json").content(jsonString))
				.andExpect(status().isOk()).andReturn();

		// WHEN
		mockMvc.perform(
				delete("/firestation").contentType("application/json").content(jsonMapper.writeValueAsString(-1)))
				.andExpect(status().isNotAcceptable());
	}
}
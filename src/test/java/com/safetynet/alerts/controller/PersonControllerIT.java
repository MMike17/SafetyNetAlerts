package com.safetynet.alerts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.Person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

	TestDataGenerator dataGenerator = new TestDataGenerator();
	ObjectMapper jsonMapper = new ObjectMapper();

	/**
	 * Tests Post request with valid object
	 * 
	 * @see PersonController#savePerson(Person)
	 */
	@Test
	public void testPostValidPerson() throws Exception {

		// GIVEN
		Person testData = dataGenerator.generateTestPerson();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// WHEN
		MvcResult result = mockMvc.perform(post("/person").contentType("application/json").content(jsonString))
				.andExpect(status().isOk()).andReturn();

		// THEN
		String stringResult = result.getResponse().getContentAsString();
		Person responsePerson = jsonMapper.readValue(stringResult, Person.class);

		// replaces index because it should be updated
		if (testData.getId() != responsePerson.getId())
			testData.setId(responsePerson.getId());

		if (!testData.compare(responsePerson))
			fail("Expected test data and result to be the same but they were not");
	}

	/**
	 * Tests Post request with invalid object
	 * 
	 * @see PersonController#savePerson(Person)
	 */
	@Test
	public void testPostInvalidPerson() throws Exception {

		// GIVEN
		Person testData = dataGenerator.generateTestPerson();
		testData.setEmail(null);
		String jsonString = jsonMapper.writeValueAsString(testData);

		// WHEN
		mockMvc.perform(post("/person").contentType("application/json").content(jsonString))
				.andExpect(status().isNotAcceptable());
	}

	/**
	 * Tests Put request with valid object
	 * 
	 * @see PersonController#updatePerson(Person)
	 */
	@Test
	public void testPutValidPerson() throws Exception {

		// GIVEN
		String expectedEmail = "new.mail@testmail.com";
		Person testData = dataGenerator.generateTestPerson();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// save initial object
		MvcResult initResult = mockMvc.perform(post("/person").contentType("application/json").content(jsonString))
				.andReturn();

		String resultString = initResult.getResponse().getContentAsString();
		testData = jsonMapper.readValue(resultString, Person.class);
		testData.setEmail(expectedEmail);
		jsonString = jsonMapper.writeValueAsString(testData);

		// WHEN
		MvcResult result = mockMvc.perform(put("/person").contentType("application/json").content(jsonString))
				.andExpect(status().isOk()).andReturn();

		// THEN
		String stringResult = result.getResponse().getContentAsString();
		Person responsePerson = jsonMapper.readValue(stringResult, Person.class);

		assertEquals(expectedEmail, responsePerson.getEmail());
	}

	/**
	 * Tests Put request with invalid object
	 * 
	 * @see PersonController#updatePerson(Person)
	 */
	@Test
	public void testPutInvalidPerson() throws Exception {

		// GIVEN
		String expectedEmail = null;
		Person testData = dataGenerator.generateTestPerson();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// save initial object
		MvcResult initResult = mockMvc.perform(post("/person").contentType("application/json").content(jsonString))
				.andReturn();

		String resultString = initResult.getResponse().getContentAsString();
		testData = jsonMapper.readValue(resultString, Person.class);
		testData.setEmail(expectedEmail);
		jsonString = jsonMapper.writeValueAsString(testData);

		// WHEN
		mockMvc.perform(put("/person").contentType("application/json").content(jsonString))
				.andExpect(status().isNotAcceptable());
	}

	/**
	 * Tests Delete request with valid object
	 * 
	 * @see PersonController#deletePerson(Person)
	 */
	@Test
	public void testDeleteValidPerson() throws Exception {

		// GIVEN
		Person testData = dataGenerator.generateTestPerson();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// save initial object
		MvcResult initResult = mockMvc.perform(post("/person").contentType("application/json").content(jsonString))
				.andExpect(status().isOk()).andReturn();

		String stringResponse = initResult.getResponse().getContentAsString();
		testData = jsonMapper.readValue(stringResponse, Person.class);

		// WHEN
		mockMvc.perform(delete("/person").contentType("application/json").content(
				jsonMapper.writeValueAsString(new String[] { testData.getFirstName(), testData.getLastName() })))
				.andExpect(status().isOk());
	}

	/**
	 * Tests Delete request with invalid object
	 * 
	 * @see PersonController#deletePerson(Person)
	 */
	@Test
	public void testDeleteInvalidPerson() throws Exception {

		// GIVEN
		Person testData = dataGenerator.generateTestPerson();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// save initial object
		mockMvc.perform(post("/person").contentType("application/json").content(jsonString)).andExpect(status().isOk());

		// WHEN
		mockMvc.perform(delete("/person").contentType("application/json")
				.content(jsonMapper.writeValueAsString(new String[] { "", testData.getLastName() })))
				.andExpect(status().isNotAcceptable());
	}
}
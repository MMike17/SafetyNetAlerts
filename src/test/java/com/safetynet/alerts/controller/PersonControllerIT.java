package com.safetynet.alerts.controller;

import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerIT {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	PersonRepository repository;

	@MockBean
	PersonService service;

	static TestDataGenerator dataGenerator;

	@BeforeAll
	static void setUp() {

		dataGenerator = new TestDataGenerator();
	}

	@Test
	public void testPostPersonValid() throws Exception {

		// GIVEN
		// WHEN
		// THEN
	}

	@Test
	public void testPostPersonNull() throws Exception {

		// GIVEN
		// WHEN
		// THEN
	}

	@Test
	public void testPutPerson() throws Exception {

		// GIVEN
		// WHEN
		// THEN
	}

	@Test
	public void testDeletePersonValid() throws Exception {

		// GIVEN
		// WHEN
		// THEN
	}

	@Test
	public void testDeletePersonNull() throws Exception {

		// GIVEN
		// WHEN
		// THEN
	}
}
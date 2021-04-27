package com.safetynet.alerts.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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

	/**
	 * Tests Post request
	 * 
	 * @see FireStationController#saveStation(FireStation)
	 */
	@Test
	public void testPostStation() throws Exception {

	}

	/**
	 * Tests Put request
	 * 
	 * @see FireStationController#updateStation(FireStation)
	 */
	@Test
	public void testPutStation() throws Exception {

	}

	/**
	 * Tests Delete request
	 * 
	 * @see FireStationController#deleteStation(FireStation)
	 */
	@Test
	public void testDeleteStation() throws Exception {

	}
}
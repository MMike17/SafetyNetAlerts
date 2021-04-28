package com.safetynet.alerts.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Tests MedicalRecord controller with simulated web requests
 * 
 * @see MedicalRecordController
 * 
 * @author Mike Matthews
 */
@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerIT {

	@Autowired
	MockMvc mockMvc;

	/**
	 * Tests Post request
	 * 
	 * @see MedicalRecordController#saveRecord(MedicalRecord)
	 */
	@Test
	public void testPostRecord() throws Exception {

	}

	/**
	 * Tests Put request
	 * 
	 * @see MedicalRecordController#updateRecord(MedicalRecord)
	 */
	@Test
	public void testPutRecord() throws Exception {

	}

	/**
	 * Tests Delete request
	 * 
	 * @see MedicalRecordController#deleteRecord(MedicalRecord)
	 */
	@Test
	public void testDeleteRecord() throws Exception {

	}
}
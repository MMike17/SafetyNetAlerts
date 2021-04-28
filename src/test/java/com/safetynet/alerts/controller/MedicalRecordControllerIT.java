package com.safetynet.alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

		mockMvc.perform(post("/medicalRecord")).andExpect(status().isOk());
	}

	/**
	 * Tests Put request
	 * 
	 * @see MedicalRecordController#updateRecord(MedicalRecord)
	 */
	@Test
	public void testPutRecord() throws Exception {

		mockMvc.perform(put("/medicalRecord")).andExpect(status().isOk());
	}

	/**
	 * Tests Delete request
	 * 
	 * @see MedicalRecordController#deleteRecord(MedicalRecord)
	 */
	@Test
	public void testDeleteRecord() throws Exception {

		mockMvc.perform(delete("/medicalRecord")).andExpect(status().isOk());
	}
}
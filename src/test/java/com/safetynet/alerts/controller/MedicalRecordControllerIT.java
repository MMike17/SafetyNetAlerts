package com.safetynet.alerts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.MedicalRecord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

	TestDataGenerator dataGenerator = new TestDataGenerator();
	ObjectMapper jsonMapper = new ObjectMapper();

	/**
	 * Tests Post request with valid object
	 * 
	 * @see MedicalRecordController#saveRecord(MedicalRecord)
	 */
	@Test
	public void testPostValidRecord() throws Exception {

		// GIVEN
		MedicalRecord testData = dataGenerator.generateTestRecord();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// WHEN
		MvcResult result = mockMvc.perform(post("/medicalRecord").contentType("application/json").content(jsonString))
				.andExpect(status().isOk()).andReturn();

		// THEN
		String stringResult = result.getResponse().getContentAsString();
		MedicalRecord responseRecord = jsonMapper.readValue(stringResult, MedicalRecord.class);

		if (!testData.compare(responseRecord))
			fail("Test data and response data are not the same");
	}

	/**
	 * Tests Post request with invalid object
	 * 
	 * @see MedicalRecordController#saveRecord(MedicalRecord)
	 */
	@Test
	public void testPostInvalidRecord() throws Exception {

		// GIVEN
		MedicalRecord testData = dataGenerator.generateTestRecord();
		testData.setBirthDate(null);
		String jsonString = jsonMapper.writeValueAsString(testData);

		// WHEN
		mockMvc.perform(post("/medicalRecord").contentType("application/json").content(jsonString))
				.andExpect(status().isNotAcceptable());
	}

	/**
	 * Tests Put request with valid object
	 * 
	 * @see MedicalRecordController#updateRecord(MedicalRecord)
	 */
	@Test
	public void testPutValidRecord() throws Exception {

		// GIVEN
		Date expectedBirthDate = new Date(545400800000L);
		MedicalRecord testData = dataGenerator.generateTestRecord();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// save initial object
		MvcResult initResult = mockMvc
				.perform(post("/medicalRecord").contentType("application/json").content(jsonString)).andReturn();

		String resultString = initResult.getResponse().getContentAsString();
		testData = jsonMapper.readValue(resultString, MedicalRecord.class);
		testData.setBirthDate(expectedBirthDate);
		jsonString = jsonMapper.writeValueAsString(testData);

		// WHEN
		MvcResult result = mockMvc.perform(put("/medicalRecord").contentType("application/json").content(jsonString))
				.andExpect(status().isOk()).andReturn();

		// THEN
		String stringResult = result.getResponse().getContentAsString();
		MedicalRecord responseRecord = jsonMapper.readValue(stringResult, MedicalRecord.class);

		assertEquals(expectedBirthDate.toLocalDate(), responseRecord.getBirthDate().toLocalDate());
	}

	/**
	 * Tests Put request with invalid object
	 * 
	 * @see MedicalRecordController#updateRecord(MedicalRecord)
	 */
	@Test
	public void testPutInvalidRecord() throws Exception {

		// GIVEN
		Date expectedBirthDate = null;
		MedicalRecord testData = dataGenerator.generateTestRecord();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// save initial object
		MvcResult initResult = mockMvc
				.perform(post("/medicalRecord").contentType("application/json").content(jsonString)).andReturn();

		String resultString = initResult.getResponse().getContentAsString();
		testData = jsonMapper.readValue(resultString, MedicalRecord.class);
		testData.setBirthDate(expectedBirthDate);
		jsonString = jsonMapper.writeValueAsString(testData);

		// WHEN
		mockMvc.perform(put("/medicalRecord").contentType("application/json").content(jsonString))
				.andExpect(status().isNotAcceptable());
	}

	/**
	 * Tests Delete request with valid object
	 * 
	 * @see MedicalRecordController#deleteRecord(MedicalRecord)
	 */
	@Test
	public void testDeleteValidRecord() throws Exception {

		// GIVEN
		MedicalRecord testData = dataGenerator.generateTestRecord();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// save initial object
		MvcResult initResult = mockMvc
				.perform(post("/medicalRecord").contentType("application/json").content(jsonString))
				.andExpect(status().isOk()).andReturn();

		String stringResponse = initResult.getResponse().getContentAsString();
		testData = jsonMapper.readValue(stringResponse, MedicalRecord.class);

		// WHEN
		mockMvc.perform(delete("/medicalRecord").contentType("application/json").content(
				jsonMapper.writeValueAsString(new String[] { testData.getFirstName(), testData.getLastName() })))
				.andExpect(status().isOk());
	}

	/**
	 * Tests Delete request with invalid object
	 * 
	 * @see MedicalRecordController#deleteRecord(MedicalRecord)
	 */
	@Test
	public void testDeleteInvalidRecord() throws Exception {

		// GIVEN
		MedicalRecord testData = dataGenerator.generateTestRecord();
		String jsonString = jsonMapper.writeValueAsString(testData);

		// save initial object
		mockMvc.perform(post("/medicalRecord").contentType("application/json").content(jsonString))
				.andExpect(status().isOk());

		// WHEN
		mockMvc.perform(delete("/medicalRecord").contentType("application/json")
				.content(jsonMapper.writeValueAsString(new String[] { "", testData.getLastName() })))
				.andExpect(status().isNotAcceptable());
	}
}
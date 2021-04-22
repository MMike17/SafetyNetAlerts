package com.safetynet.alerts.service;

import com.safetynet.alerts.repository.MedicalRecordRepository;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the interactions between MedicalRecordService and the database
 * 
 * @see MedicalRecordService
 * @author Mike Matthews
 */
@SpringBootTest
public class MedicalRecordServiceIT {

	@Autowired
	MedicalRecordRepository repository;

	@Autowired
	MedicalRecordService testedService;

	@BeforeAll
	void setUp() {

		repository.deleteAll();
	}

	/**
	 * Tests if the provided MedicalRecord object is saved in database
	 * 
	 * @see MedicalRecordService#addRecord(MedicalRecord)
	 */
	@Test
	public void testRecordSave() {

		// GIVEN
		// WHEN
		// THEN
	}

	/**
	 * Tests if the provided MedicalRecord object is updated in database
	 * 
	 * @see MedicalRecordService#updateRecord(MedicalRecord)
	 */
	@Test
	public void testPersonUpdate() {

		// GIVEN
		// WHEN
		// THEN
	}

	/**
	 * Tests if the provided MedicalRecord object is deleted in database
	 * 
	 * @see MedicalRecordService#removeRecord(Long)
	 */
	@Test
	public void testPersonDelete() {

		// GIVEN
		// WHEN
		// THEN
	}
}
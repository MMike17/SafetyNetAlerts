package com.safetynet.alerts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.safetynet.alerts.model.local.LocalData;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests for integration of LocalDataReader
 * 
 * @see LocalDataReader
 * 
 * @author MikeMatthews
 */
@SpringBootTest
public class LocalDataReaderIT {

	static LocalDataReader testedReader;

	@BeforeAll
	static void setUp() {
		testedReader = new LocalDataReader("/data.json");
	}

	/**
	 * Tests if the data extracted from local file is valid
	 * 
	 * @see LocalData
	 * @see LocalDataReader#readFile()
	 */
	@Test
	public void testReaderExtractedValues() {

		// GIVEN
		LocalData localData = null;
		int expectedPersonsArrayLength = 23;
		int expectedFirestationsArrayLength = 13;
		int expectedMedicalRecordsArrayLength = 23;

		// WHEN
		localData = testedReader.readFile();

		// THEN
		assertNotNull(localData);

		assertNotNull(localData.persons);
		assertNotNull(localData.firestations);
		assertNotNull(localData.medicalrecords);

		assertEquals(expectedPersonsArrayLength, localData.persons.length);
		assertEquals(expectedFirestationsArrayLength, localData.firestations.length);
		assertEquals(expectedMedicalRecordsArrayLength, localData.medicalrecords.length);
	}
}
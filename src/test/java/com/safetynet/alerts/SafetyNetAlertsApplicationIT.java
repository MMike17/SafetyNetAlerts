package com.safetynet.alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.safetynet.alerts.controller.LocalDataReader;
import com.safetynet.alerts.model.local.LocalData;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests integration of classes in main application class
 * 
 * @see SafetyNetAlertsApplication
 * 
 * @author MikeMatthews
 */
@SpringBootTest
public class SafetyNetAlertsApplicationIT {

	static SafetyNetAlertsApplication testedApplication;
	static LocalDataReader reader;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	FireStationRepository fireStationRepository;

	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	@BeforeAll
	static void setUp() {

		testedApplication = new SafetyNetAlertsApplication();
		reader = new LocalDataReader("/data.json");
	}

	/**
	 * Tests if the SpringBoot context is loaded correctly
	 */
	@Test
	public void contextLoads() {
	}

	/**
	 * Tests if the app injects local data in the database at startup
	 */
	@Test
	public void testInjectsLocalDataInDatabase() {

		LocalData localData = reader.readFile();
		long expectedPersonCount = localData.persons.length;
		long expectedStationCount = localData.firestations.length;
		long expectedRecord = localData.medicalrecords.length;

		assertEquals(personRepository.count(), expectedPersonCount);
		assertEquals(fireStationRepository.count(), expectedStationCount);
		assertEquals(medicalRecordRepository.count(), expectedRecord);
	}
}
package com.safetynet.alerts;

import java.sql.Date;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

/**
 * Class used by tests to generate dummy data
 * 
 * @see Person
 * @see FireStation
 * @see MedicalRecord
 * 
 * @author Mike Matthews
 */
public class TestDataGenerator {

	/**
	 * Generates a test Person containing dummy data
	 * 
	 * @return a Person object with dummy data
	 */
	public Person generateTestPerson() {

		Person testPerson = new Person("Test", "TEST");
		testPerson.setId(Long.valueOf(0));
		testPerson.setAddress("X Test road");
		testPerson.setCity("Test city");
		testPerson.setZipCode(123);
		testPerson.setPhone("000-000-0001");
		testPerson.setEmail("testmail@test.com");

		return testPerson;
	}

	/**
	 * Generates a test FireStation containing dummy data
	 * 
	 * @return a FireStation object with dummy data
	 */
	public FireStation generateTestStation() {

		FireStation testStation = new FireStation("Testville", 0);
		testStation.setId(Long.valueOf(0));

		return testStation;
	}

	/**
	 * Generates a test MedicalRecord containing dummy data
	 * 
	 * @return a MedicalRecord object with dummy data
	 */
	public MedicalRecord generateTestRecord() {

		MedicalRecord testRecord = new MedicalRecord("Test", "TEST");
		testRecord.setId(Long.valueOf(0));
		testRecord.setBirthDate(new Date(645400800000L));
		testRecord.setMedications(new String[] { "test" });
		testRecord.setAllergies(new String[] { "test" });

		return testRecord;
	}
}
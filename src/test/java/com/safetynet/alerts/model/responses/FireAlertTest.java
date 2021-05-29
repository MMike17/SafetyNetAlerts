package com.safetynet.alerts.model.responses;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.Person;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for response model FireAlert
 * 
 * @see FireAlert
 * 
 * @author MikeMatthews
 */
public class FireAlertTest {

	TestDataGenerator dataGenerator = new TestDataGenerator();

	/**
	 * Tests if the compare method works with similar objects
	 */
	@Test
	public void testCompareValidObject() {

		// GIVEN
		FireAlert referenceObject = generateTestFireAlert();
		FireAlert testData = generateTestFireAlert();

		// WHEN
		boolean result = referenceObject.compare(testData);

		// THEN
		assertTrue(result);
	}

	/**
	 * Tests if the compare method works with different stationID
	 */
	@Test
	public void testCompareInvalidStationID() {

		// GIVEN
		FireAlert referenceObject = generateTestFireAlert();
		FireAlert testData = generateTestFireAlert();
		testData.setCorrespondingStationID(3);

		// WHEN
		boolean result = referenceObject.compare(testData);

		// THEN
		assertFalse(result);
	}

	/**
	 * Tests if the compare method works with different people size
	 */
	@Test
	public void testCompareInvalidPeopleSize() {

		// GIVEN
		FireAlert referenceObject = generateTestFireAlert();
		FireAlert testData = generateTestFireAlert();

		ArrayList<FullPerson> people = testData.getInhabitants();
		people.add(new FullPerson(dataGenerator.generateTestPerson(), dataGenerator.generateTestRecord(), 0));

		// WHEN
		boolean result = referenceObject.compare(testData);

		// THEN
		assertFalse(result);
	}

	/**
	 * Tests if the compare method works with different people
	 */
	@Test
	public void testCompareInvalidPeople() {

		// GIVEN
		FireAlert referenceObject = generateTestFireAlert();
		FireAlert testData = generateTestFireAlert();

		FullPerson fullPerson = testData.getInhabitants().get(0);
		Person person = fullPerson.getPerson();
		person.setFirstName("TestFirstName");
		person.setLastName("TestLastName");

		// WHEN
		boolean result = referenceObject.compare(testData);

		// THEN
		assertFalse(result);
	}

	FireAlert generateTestFireAlert() {

		FireAlert result = new FireAlert();
		result.setCorrespondingStationID(1);

		ArrayList<FullPerson> people = new ArrayList<FullPerson>();
		people.add(new FullPerson(dataGenerator.generateTestPerson(), dataGenerator.generateTestRecord(), 0));
		result.setInhabitants(people);

		return result;
	}
}
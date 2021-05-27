package com.safetynet.alerts.model.responses;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.Person;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for response model HouseHold
 * 
 * @see HouseHold
 * 
 * @author MikeMatthews
 */
public class HouseHoldTest {

	TestDataGenerator dataGenerator = new TestDataGenerator();

	/**
	 * Tests if the compare method works with similar objects
	 */
	@Test
	public void testCompareValidObjects() {

		// GIVEN
		HouseHold referenceObject = generateTestHouseHold();
		HouseHold testData = generateTestHouseHold();

		// WHEN
		boolean result = referenceObject.compare(testData);

		// THEN
		assertTrue(result);
	}

	/**
	 * Tests if the compare method works with different address
	 */
	@Test
	public void testCompareInvalidAddress() {

		// GIVEN
		HouseHold referenceObject = generateTestHouseHold();
		HouseHold testData = generateTestHouseHold();
		testData.setAddress("TestAddress2");

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
		HouseHold referenceObject = generateTestHouseHold();
		HouseHold testData = generateTestHouseHold();

		ArrayList<FullPerson> people = testData.getPeople();
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
		HouseHold referenceObject = generateTestHouseHold();
		HouseHold testData = generateTestHouseHold();

		FullPerson fullPerson = testData.getPeople().get(0);
		Person person = fullPerson.getPerson();
		person.setFirstName("TestFirstName");
		person.setLastName("TestLastName");

		// WHEN
		boolean result = referenceObject.compare(testData);

		// THEN
		assertFalse(result);
	}

	HouseHold generateTestHouseHold() {

		HouseHold result = new HouseHold();
		result.setAddress("TestAddress");

		ArrayList<FullPerson> people = new ArrayList<FullPerson>();
		people.add(new FullPerson(dataGenerator.generateTestPerson(), dataGenerator.generateTestRecord(), 0));
		result.setPeople(people);

		return result;
	}
}
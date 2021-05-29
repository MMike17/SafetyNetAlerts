package com.safetynet.alerts.model.responses;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.Person;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for response model PeopleCoveredByStation
 * 
 * @see PeopleCoveredByStation
 * 
 * @author MikeMatthews
 */
public class PeopleCoveredByStationTest {

	TestDataGenerator dataGenerator = new TestDataGenerator();

	/**
	 * Tests if the compare method works with similar objects
	 */
	@Test
	public void testCompareValidObject() {

		// GIVEN
		PeopleCoveredByStation referenceData = generateTestPeopleCoveredByStation();
		PeopleCoveredByStation testData = generateTestPeopleCoveredByStation();

		// WHEN
		boolean result = referenceData.compare(testData);

		// THEN
		assertTrue(result);
	}

	/**
	 * Tests if the compare method works with different people size
	 */
	@Test
	public void testCompareInvalidPeopleSize() {

		// GIVEN
		PeopleCoveredByStation referenceData = generateTestPeopleCoveredByStation();
		PeopleCoveredByStation testData = generateTestPeopleCoveredByStation();

		ArrayList<Person> people = testData.getCoveredPeople();
		people.add(dataGenerator.generateTestPerson());

		// WHEN
		boolean result = referenceData.compare(testData);

		// THEN
		assertFalse(result);
	}

	/**
	 * Tests if the compare method works with different people
	 */
	@Test
	public void testCompareInvalidPeople() {

		// GIVEN
		PeopleCoveredByStation referenceData = generateTestPeopleCoveredByStation();
		PeopleCoveredByStation testData = generateTestPeopleCoveredByStation();

		Person person = testData.getCoveredPeople().get(0);
		person.setFirstName("TestFirstName");
		person.setLastName("TestLastName");

		// WHEN
		boolean result = referenceData.compare(testData);

		// THEN
		assertFalse(result);
	}

	/**
	 * Tests if the compare method works with different adult count
	 */
	@Test
	public void testCompareInvalidAdultCount() {

		// GIVEN
		PeopleCoveredByStation referenceData = generateTestPeopleCoveredByStation();
		PeopleCoveredByStation testData = generateTestPeopleCoveredByStation();
		testData.setAdultsCount(2);

		// WHEN
		boolean result = referenceData.compare(testData);

		// THEN
		assertFalse(result);
	}

	/**
	 * Tests if the compare method works with different children count
	 */
	@Test
	public void testCompareInvalidChildrenCount() {

		// GIVEN
		PeopleCoveredByStation referenceData = generateTestPeopleCoveredByStation();
		PeopleCoveredByStation testData = generateTestPeopleCoveredByStation();
		testData.setChildrenCount(2);

		// WHEN
		boolean result = referenceData.compare(testData);

		// THEN
		assertFalse(result);
	}

	PeopleCoveredByStation generateTestPeopleCoveredByStation() {

		PeopleCoveredByStation testData = new PeopleCoveredByStation();

		ArrayList<Person> coveredPeople = new ArrayList<Person>();
		coveredPeople.add(dataGenerator.generateTestPerson());

		testData.setCoveredPeople(coveredPeople);
		testData.setAdultsCount(0);
		testData.setChildrenCount(1);

		return testData;
	}
}
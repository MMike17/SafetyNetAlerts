package com.safetynet.alerts.model.responses;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for response model FullPerson
 * 
 * @see FullPerson
 * 
 * @author MikeMatthews
 */
public class FullPersonTest {

	TestDataGenerator dataGenerator = new TestDataGenerator();

	/**
	 * Tests if the compare method works with similar objects
	 */
	@Test
	public void testCompareValidObject() {

		// GIVEN
		FullPerson referenceObject = generateTestFullPerson();
		FullPerson testObject = generateTestFullPerson();

		// WHEN
		boolean result = referenceObject.compare(testObject);

		// THEN
		assertTrue(result);
	}

	/**
	 * Tests if the compare method works with different ages
	 */
	@Test
	public void testCompareInvalidAge() {

		// GIVEN
		FullPerson referenceObject = generateTestFullPerson();
		FullPerson testObject = generateTestFullPerson();
		testObject.setAge(1);

		// WHEN
		boolean result = referenceObject.compare(testObject);

		// THEN
		assertFalse(result);
	}

	/**
	 * Tests if the compare method works with different Person
	 */
	@Test
	public void testCompareInvalidPerson() {

		// GIVEN
		FullPerson referenceObject = generateTestFullPerson();
		FullPerson testObject = generateTestFullPerson();

		Person person = dataGenerator.generateTestPerson();
		person.setFirstName("TestFirstName");
		person.setLastName("TestLastName");
		testObject.setPerson(person);

		// WHEN
		boolean result = referenceObject.compare(testObject);

		// THEN
		assertFalse(result);
	}

	/**
	 * Tests if the compare method works with null MedicalRecord
	 */
	@Test
	public void testCompareNullMedicalRecord() {

		// GIVEN
		FullPerson referenceObject = generateTestFullPerson();
		FullPerson testObject = generateTestFullPerson();
		testObject.setMedicalRecord(null);

		// WHEN
		boolean result = referenceObject.compare(testObject);

		// THEN
		assertTrue(result);
	}

	/**
	 * Tests if the compare method works with different MedicalRecord
	 */
	@Test
	public void testCompareInvalidMedicalRecord() {

		// GIVEN
		FullPerson referenceObject = generateTestFullPerson();
		FullPerson testObject = generateTestFullPerson();

		MedicalRecord record = dataGenerator.generateTestRecord();
		record.setFirstName("TestFirstName");
		record.setLastName("TestLastName");
		testObject.setMedicalRecord(record);

		// WHEN
		boolean result = referenceObject.compare(testObject);

		// THEN
		assertFalse(result);
	}

	FullPerson generateTestFullPerson() {

		return new FullPerson(dataGenerator.generateTestPerson(), dataGenerator.generateTestRecord(), 0);
	}
}
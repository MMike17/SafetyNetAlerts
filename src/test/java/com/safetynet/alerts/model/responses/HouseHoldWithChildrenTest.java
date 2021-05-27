package com.safetynet.alerts.model.responses;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.Person;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for response model HouseHoldWithChildren
 * 
 * @see HouseHoldWithChildren
 * 
 * @author MikeMatthews
 */
public class HouseHoldWithChildrenTest {

	TestDataGenerator dataGenerator = new TestDataGenerator();

	/**
	 * Tests if the compare method works with similar objects
	 */
	@Test
	public void testCompareValidObjects() {

		// GIVEN
		HouseHoldWithChildren referenceObject = generateTestHouseHold();

		HouseHoldWithChildren testObject = new HouseHoldWithChildren();
		testObject.setAddress(referenceObject.getAddress());
		testObject.setChildren(referenceObject.getChildren());
		testObject.setAdults(referenceObject.getAdults());

		// WHEN
		boolean result = referenceObject.compare(testObject);

		// THEN
		assertTrue(result);
	}

	/**
	 * Tests if the compare method works with different addresses
	 */
	@Test
	public void testCompareInvalidAddress() {

		// GIVEN
		HouseHoldWithChildren referenceObject = generateTestHouseHold();

		HouseHoldWithChildren testObject = new HouseHoldWithChildren();
		testObject.setAddress("TestAddress 2");
		testObject.setChildren(referenceObject.getChildren());
		testObject.setAdults(referenceObject.getAdults());

		// WHEN
		boolean result = referenceObject.compare(testObject);

		// THEN
		assertFalse(result);
	}

	/**
	 * Tests if the compare method works with different children size
	 */
	@Test
	public void testCompareInvalidChildrenSize() {

		// GIVEN
		HouseHoldWithChildren referenceObject = generateTestHouseHold();
		HouseHoldWithChildren testObject = new HouseHoldWithChildren();

		ArrayList<FullPerson> children = new ArrayList<FullPerson>();
		children.add(referenceObject.getChildren().get(0));
		children.add(new FullPerson(dataGenerator.generateTestPerson(), dataGenerator.generateTestRecord(), 0));

		testObject.setAddress(referenceObject.getAddress());
		testObject.setChildren(children);
		testObject.setAdults(referenceObject.getAdults());

		// WHEN
		boolean result = referenceObject.compare(testObject);

		// THEN
		assertFalse(result);
	}

	/**
	 * Tests if the compare method works with different children
	 */
	@Test
	public void testCompareInvalidChildren() {

		// GIVEN
		HouseHoldWithChildren referenceObject = generateTestHouseHold();
		HouseHoldWithChildren testObject = new HouseHoldWithChildren();

		ArrayList<FullPerson> children = new ArrayList<FullPerson>();
		FullPerson child = new FullPerson(dataGenerator.generateTestPerson(), dataGenerator.generateTestRecord(), 0);
		Person childPerson = child.getPerson();
		childPerson.setFirstName("TestFirstName");
		childPerson.setLastName("TestLastName");
		children.add(child);

		testObject.setChildren(children);
		testObject.setAddress(referenceObject.getAddress());
		testObject.setAdults(referenceObject.getAdults());

		// WHEN
		boolean result = referenceObject.compare(testObject);

		// THEN
		assertFalse(result);
	}

	/**
	 * Tests if the compare method works with different adults size
	 */
	@Test
	public void testCompareInvalidAdultsSize() {

		// GIVEN
		HouseHoldWithChildren referenceObject = generateTestHouseHold();
		HouseHoldWithChildren testObject = new HouseHoldWithChildren();

		ArrayList<Person> adults = new ArrayList<Person>(referenceObject.getAdults());
		adults.add(dataGenerator.generateTestPerson());

		testObject.setAddress(referenceObject.getAddress());
		testObject.setChildren(referenceObject.getChildren());
		testObject.setAdults(adults);

		// WHEN
		boolean result = referenceObject.compare(testObject);

		// THEN
		assertFalse(result);
	}

	/**
	 * Tests if the compare method works with different adults
	 */
	@Test
	public void testCompareInvalidAdults() {

		// GIVEN
		HouseHoldWithChildren referenceObject = generateTestHouseHold();
		HouseHoldWithChildren testObject = new HouseHoldWithChildren();

		ArrayList<Person> adults = new ArrayList<Person>();
		Person adult = dataGenerator.generateTestPerson();
		adult.setFirstName("TestFirstName");
		adult.setLastName("TestLastName");
		adults.add(adult);

		testObject.setAdults(adults);
		testObject.setChildren(referenceObject.getChildren());
		testObject.setAddress(referenceObject.getAddress());

		// WHEN
		boolean result = referenceObject.compare(testObject);

		// THEN
		assertFalse(result);
	}

	/**
	 * Generates test HouseHoldWithChildren object
	 */
	HouseHoldWithChildren generateTestHouseHold() {

		HouseHoldWithChildren testObject = new HouseHoldWithChildren();
		testObject.setAddress("TestAddress");

		ArrayList<FullPerson> children = new ArrayList<FullPerson>();
		children.add(new FullPerson(dataGenerator.generateTestPerson(), dataGenerator.generateTestRecord(), 0));
		testObject.setChildren(children);

		ArrayList<Person> adults = new ArrayList<Person>();
		adults.add(dataGenerator.generateTestPerson());
		testObject.setAdults(adults);

		return testObject;
	}
}
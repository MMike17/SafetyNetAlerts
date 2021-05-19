package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Optional;

import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the interactions between PersonService and the database
 * 
 * @see PersonService
 * @author MikeMatthews
 */
@SpringBootTest
public class PersonServiceIT {

	@Autowired
	PersonRepository repository;

	@Autowired
	PersonService testedService;

	static TestDataGenerator dataGenerator;

	@BeforeAll
	static void setUp() {

		dataGenerator = new TestDataGenerator();
	}

	@BeforeEach
	void setUpPerTest() {

		repository.deleteAll();
	}

	/**
	 * Tests if the provided Person object is saved in database
	 * 
	 * @see PersonService#addPerson(Person)
	 */
	@Test
	public void testPersonSave() {

		// GIVEN
		Person testPerson = dataGenerator.generateTestPerson();

		// WHEN
		testPerson = testedService.addPerson(testPerson);

		// THEN
		Optional<Person> resultPerson = repository.findById(testPerson.getId());

		if (!resultPerson.isPresent())
			fail("The test data was not saved, but the repository said the data was saved");

		assertEquals(testPerson, resultPerson.get());
	}

	/**
	 * Tests if the provided Person object is updated in database
	 * 
	 * @see PersonService#updatePersonProfile(Person)
	 */
	@Test
	public void testPersonUpdate() {

		// GIVEN
		String expectedCity = "Paris";
		Person testPerson = repository.save(dataGenerator.generateTestPerson());
		testPerson.setCity(expectedCity);

		// WHEN
		Person dbPerson = testedService.updatePersonProfile(testPerson);

		// THEN
		assertEquals(expectedCity, dbPerson.getCity());
	}

	/**
	 * Tests if the provided Person object is deleted in database
	 * 
	 * @see PersonService#removePerson(String, String)
	 */
	@Test
	public void testPersonDelete() {

		// GIVEN
		Person testPerson = repository.save(dataGenerator.generateTestPerson());
		Long expectedCount = repository.count() - 1;
		Optional<Person> dbTestPerson = repository.findById(testPerson.getId());

		if (!dbTestPerson.isPresent())
			fail("The repository failed to save data for test");

		// WHEN
		boolean succeeded = testedService.removePerson(testPerson.getFirstName(), testPerson.getLastName());

		// THEN
		if (!succeeded)
			fail("The repository failed to delete the data");

		assertEquals((long) expectedCount, (long) repository.count());
	}

	/**
	 * Tests if the right people are returned for provided valid address
	 * 
	 * @see PersonService#getPeopleAtAddress(String)
	 */
	@Test
	public void testGetPeopleAtValidAddress() {

		// GIVEN
		ArrayList<Person> expectedPeople = new ArrayList<Person>();

		Person testPerson1 = repository.save(dataGenerator.generateTestPerson());

		Person testPerson2 = repository.save(dataGenerator.generateTestPerson());

		Person testPerson3 = dataGenerator.generateTestPerson();
		testPerson3.setAddress("Y Test road");
		testPerson3 = repository.save(testPerson3);

		expectedPeople.add(testPerson1);
		expectedPeople.add(testPerson2);

		// WHEN
		ArrayList<Person> resultPeople = testedService.getPeopleAtAddress("X Test road");

		// THEN
		assertEquals(expectedPeople, resultPeople);
	}

	/**
	 * Tests if the right people are returned for provided invalid address
	 * 
	 * @see PersonService#getPeopleAtAddress(String)
	 */
	@Test
	public void testGetPeopleAtInvalidAddress() {

		// GIVEN
		repository.save(dataGenerator.generateTestPerson());
		repository.save(dataGenerator.generateTestPerson());
		repository.save(dataGenerator.generateTestPerson());

		// WHEN
		ArrayList<Person> resultPeople = testedService.getPeopleAtAddress("Y Test road");

		// THEN
		if (resultPeople.size() > 0)
			fail("Expected array size was 0 but was " + resultPeople.size());
	}

	/**
	 * Tests if the right people are returned with valid first and last name
	 * 
	 * @see PersonService#getPeopleFromName(String, String)
	 */
	@Test
	public void testGetPeopleFromValidFirstAndLastName() {

		// GIVEN
		ArrayList<Person> expectedPeople = new ArrayList<Person>();

		Person testPerson1 = repository.save(dataGenerator.generateTestPerson());

		Person testPerson2 = repository.save(dataGenerator.generateTestPerson());

		Person testPerson3 = dataGenerator.generateTestPerson();
		testPerson3.setFirstName("Tst");
		testPerson3 = repository.save(testPerson3);

		expectedPeople.add(testPerson1);
		expectedPeople.add(testPerson2);

		// WHEN
		ArrayList<Person> resultPeople = testedService.getPeopleFromName("Test", "TEST");

		// THEN
		assertEquals(expectedPeople, resultPeople);
	}

	/**
	 * Tests if the right people are returned with invalid first and last name
	 * 
	 * @see PersonService#getPeopleFromName(String, String)
	 */
	@Test
	public void testGetPeopleFromInvalidFirstAndLastName() {

		// GIVEN
		repository.save(dataGenerator.generateTestPerson());
		repository.save(dataGenerator.generateTestPerson());
		repository.save(dataGenerator.generateTestPerson());

		// WHEN
		ArrayList<Person> resultPeople = testedService.getPeopleFromName("X", "TEST");

		// THEN
		if (resultPeople != null && resultPeople.size() > 0)
			fail("Expected array size was 0 but was " + resultPeople.size());
	}

	/**
	 * Tests if the right people are returned with valid city name
	 * 
	 * @see PersonService#getPeopleFromCity(String)
	 */
	@Test
	public void testGetPeopleFromValidCity() {

		// GIVEN
		ArrayList<Person> expectedPeople = new ArrayList<Person>();

		Person testPerson1 = repository.save(dataGenerator.generateTestPerson());
		Person testPerson2 = repository.save(dataGenerator.generateTestPerson());

		Person testPerson3 = dataGenerator.generateTestPerson();
		testPerson3.setCity("XX");
		testPerson3 = repository.save(testPerson3);

		expectedPeople.add(testPerson1);
		expectedPeople.add(testPerson2);

		// WHEN
		ArrayList<Person> resultPeople = testedService.getPeopleFromCity("Test city");

		// THEN
		assertEquals(expectedPeople, resultPeople);
	}

	/**
	 * Tests if the right people are returned with invalid city name
	 * 
	 * @see PersonService#getPeopleFromCity(String)
	 */
	@Test
	public void testGetPeopleFromInvalidCity() {

		// GIVEN
		repository.save(dataGenerator.generateTestPerson());
		repository.save(dataGenerator.generateTestPerson());
		repository.save(dataGenerator.generateTestPerson());

		// WHEN
		ArrayList<Person> resultPeople = testedService.getPeopleFromCity("XX");

		// THEN
		if (resultPeople != null && resultPeople.size() > 0)
			fail("Expected array size was 0 but was " + resultPeople.size());
	}
}
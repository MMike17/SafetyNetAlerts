package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

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
	void testPersonSave() {

		// GIVEN
		Person testPerson = generateTestPerson();

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
	void testPersonUpdate() {

		// GIVEN
		String expectedCity = "Paris";
		Person testPerson = repository.save(generateTestPerson());
		testPerson.setCity(expectedCity);

		// WHEN
		boolean succeeded = testedService.updatePersonProfile(testPerson);

		// THEN
		if (!succeeded)
			fail("The repository failed to update the data");

		Optional<Person> resultPerson = repository.findById(testPerson.getId());

		if (!resultPerson.isPresent())
			fail("The test data was not updated, but the repository said the data was updated");

		assertEquals(expectedCity, resultPerson.get().getCity());
	}

	/**
	 * Tests if the provided Person object is deleted in database
	 * 
	 * @see PersonService#removePerson(String, String)
	 */
	@Test
	void testPersonDelete() {

		// GIVEN
		Person testPerson = repository.save(generateTestPerson());
		Long expectedCount = repository.count() - 1;
		Optional<Person> dbTestPerson = repository.findById(testPerson.getId());

		if (!dbTestPerson.isPresent())
			fail("The repository failed to save data for test");

		// WHEN
		boolean succeeded = testedService.removePerson(testPerson.getFirstName(), testPerson.getLastName());

		// THEN
		if (!succeeded)
			fail("The repository failed to delete the data");

		assertEquals(expectedCount, repository.count());
	}

	/**
	 * Generates a test person containing dummy data
	 * 
	 * @return a Person object with dummy data
	 */
	Person generateTestPerson() {

		Person testPerson = new Person("Test", "TEST");
		testPerson.setId(Long.valueOf(0));
		testPerson.setAddress("X Test road");
		testPerson.setCity("Test city");
		testPerson.setZipCode(123);
		testPerson.setPhone("000-000-0001");
		testPerson.setEmail("testmail@test.com");

		return testPerson;
	}
}
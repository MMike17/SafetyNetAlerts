package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the interactions between PersonService and the database
 * 
 * @see com.safetynet.alerts.service.PersonService
 * @author MikeMatthews
 */
@SpringBootTest
public class PersonServiceIT {

	@Autowired
	PersonRepository repository;

	@Autowired
	PersonService testedService;

	static int nextTestPersonIndex;

	@AfterAll
	static void cleanUp() {

		nextTestPersonIndex = 0;
	}

	/**
	 * Tests if the provided Person object is saved in database
	 * 
	 * @see com.safetynet.alerts.service.PersonService#addPerson(Person)
	 */
	@Test
	void testPersonSave() {

		// GIVEN
		Person testPerson = generateTestPerson();

		// WHEN
		boolean succeeded = testedService.addPerson(testPerson);

		// THEN
		if (!succeeded)
			fail("The repository failed to save the data");

		Optional<Person> resultPerson = repository.findById(Long.valueOf(0));

		assertEquals(testPerson, resultPerson.get());
	}

	@Test
	void testPersonUpdate() {

		// GIVEN
		String expectedCity = "Paris";
		Person testPerson = generateTestPerson();
		repository.save(testPerson);
		testPerson.setCity(expectedCity);

		// WHEN
		boolean succeeded = testedService.updatePersonProfile(testPerson);

		// THEN
		if (!succeeded)
			fail("The repository failed to update the data");

		Optional<Person> resultPerson = repository.findById(testPerson.getId());

		assertEquals(expectedCity, resultPerson.get().getCity());
	}

	@Test
	void testPersonDelete() {

		// GIVEN
		// WHEN
		// THEN
	}

	static Person generateTestPerson() {

		Person testPerson = new Person();
		testPerson.setId(Long.valueOf(nextTestPersonIndex));
		testPerson.setFirstName("Test");
		testPerson.setLastName("TEST");
		testPerson.setAddress("X Test road");
		testPerson.setCity("Test city");
		testPerson.setZipCode(123);
		testPerson.setPhone("000-000-0001");
		testPerson.setEmail("testmail@test.com");

		return testPerson;
	}
}
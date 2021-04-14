package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the interractions between PersonService and the database
 * 
 * @see com.safetynet.alerts.service.PersonService
 * @author MikeMatthews
 */
@SpringBootTest
class PersonServiceIT {

	@Autowired
	static PersonRepository repository;

	static PersonService testedService;
	static int nextTestPersonIndex;

	@BeforeAll
	static void setUp() {

		testedService = new PersonService();

		Person testPerson = generateTestPerson();

		repository.save(testPerson);
	}

	@AfterAll
	static void cleanUp() {
		nextTestPersonIndex = 0;

		repository.deleteAll();
	}

	@Test
	void testPersonSave() {

		// GIVEN
		// WHEN
		// THEN
	}

	@Test
	void testPersonUpdate() {

		// GIVEN
		// WHEN
		// THEN
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
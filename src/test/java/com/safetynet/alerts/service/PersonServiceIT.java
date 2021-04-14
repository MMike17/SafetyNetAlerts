package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

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

	// @Autowired
	static PersonRepository repository;

	static PersonService testedService;
	static int nextTestPersonIndex;

	@BeforeAll
	static void setUp() {

		testedService = new PersonService();
		repository = new PersonRepository() {

			// Find a way to move all of this away
			@Override
			public long count() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void delete(Person arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAll() {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAll(Iterable<? extends Person> arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteAllById(Iterable<? extends Long> arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deleteById(Long arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean existsById(Long arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Iterable<Person> findAll() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Iterable<Person> findAllById(Iterable<Long> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Optional<Person> findById(Long arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Person> S save(S arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <S extends Person> Iterable<S> saveAll(Iterable<S> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

		};

		Person testPerson = generateTestPerson();

		repository.save(testPerson);
	}

	@AfterAll
	static void cleanUp() {

		nextTestPersonIndex = 0;

		repository.deleteAll();
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

		Optional<Person> resultPerson = repository.findById(Long.valueOf(1));

		assertEquals(testPerson, resultPerson.get());
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
package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the interractions between PersonService and the database
 * 
 * @see com.safetynet.alerts.service.PersonService
 * @author MikeMatthews
 */
@SpringBootTest
class PersonServiceIT {

	static PersonService testedService;

	@BeforeAll
	static void setUp() {
		testedService = new PersonService();

		// SQL objects insertion here
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
}
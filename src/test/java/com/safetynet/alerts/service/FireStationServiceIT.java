package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the interactions between FireStation and the database
 * 
 * @see FireStationService
 * @author MikeMatthews
 */
@SpringBootTest
public class FireStationServiceIT {

	@Autowired
	FireStationRepository repository;

	@Autowired
	FireStationService testedService;

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
	 * Tests if the provided ForeStation object is saved in database
	 * 
	 * @see FireStationService#addFireStation(FireStation)
	 */
	@Test
	public void testStationSave() {

		// GIVEN
		FireStation testStation = dataGenerator.generateTestStation();

		// WHEN
		testStation = testedService.addFireStation(testStation);

		// THEN
		Optional<FireStation> resultStation = repository.findById(testStation.getId());

		if (!resultStation.isPresent())
			fail("The test data was not saved but the repository said the data was saved");

		assertEquals(testStation, resultStation.get());
	}

	/**
	 * Tests if the provided FireStation object is updated in database
	 * 
	 * @see FireStationService#updateFireStation(FireStation)
	 */
	@Test
	public void testStationUpdate() {

		// GIVEN
		String expectedAdress = "Other test";
		FireStation testStation = repository.save(dataGenerator.generateTestStation());
		testStation.setAddress(expectedAdress);

		// WHEN
		FireStation dbStation = testedService.updateFireStation(testStation);

		// THEN
		assertEquals(testStation.getAddress(), dbStation.getAddress());
	}

	/**
	 * Tests if the provided FireStation object is deleted in database
	 * 
	 * @see FireStationService#removeFireStation(Long)
	 */
	@Test
	public void testStationDelete() {

		// GIVEN
		FireStation testStation = repository.save(dataGenerator.generateTestStation());
		Long expectedCount = repository.count() - 1;
		Optional<FireStation> dbTestStation = repository.findById(testStation.getId());

		if (!dbTestStation.isPresent())
			fail("The repository failed to save data for test");

		// WHEN
		testedService.removeFireStation(testStation.getId());

		// THEN
		assertEquals(expectedCount, repository.count());
	}
}
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
	 * @see FireStationService#updateRecord(FireStation)
	 */
	@Test
	public void testStationUpdate() {

		// GIVEN
		Integer expectedIndex = 1;
		FireStation testStation = repository.save(dataGenerator.generateTestStation());
		testStation.setStationId(expectedIndex);

		// WHEN
		boolean succeeded = testedService.updateRecord(testStation);

		// THEN
		if (!succeeded)
			fail("The repository failed to update the data");

		Optional<FireStation> resultStation = repository.findById(testStation.getId());

		if (!resultStation.isPresent())
			fail("The test data was not updated but the repository said the data was updated");

		assertEquals(expectedIndex, resultStation.get().getStationId());
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
		boolean succeeded = testedService.removeFireStation(testStation.getId());

		// THEN
		if (!succeeded)
			fail("The repository failed to delete the data");

		assertEquals(expectedCount, repository.count());
	}
}
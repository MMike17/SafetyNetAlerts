package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
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

		if (!testStation.getAddress().equals(resultStation.get().getAddress())
				|| !testStation.getStationId().equals(resultStation.get().getStationId()))
			fail("Expected test data and result to be the same but they were not");
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
		assertEquals((long) expectedCount, (long) repository.count());
	}

	/**
	 * Tests if the right addresses are returned from a valid station ID
	 * 
	 * @see FireStationService#getAddressesFromStationID(Integer)
	 */
	@Test
	void testGetAddressesFromValidStationID() {

		// GIVEN
		ArrayList<String> expectedAddresses = new ArrayList<String>();

		FireStation testStation1 = repository.save(dataGenerator.generateTestStation());

		FireStation testStation2 = dataGenerator.generateTestStation();
		testStation2.setStationId(2);
		testStation2.setAddress("Y Test road");
		testStation2 = repository.save(testStation2);

		FireStation testStation3 = dataGenerator.generateTestStation();
		testStation3.setAddress("Z Test road");
		testStation3.setStationId(2);
		testStation3 = repository.save(testStation3);

		expectedAddresses.add(testStation1.getAddress());

		// WHEN
		ArrayList<String> resultAddresses = testedService.getAddressesFromStationID(1);

		// THEN
		assertEquals(expectedAddresses, resultAddresses);
	}

	/**
	 * Tests if the right addresses are returned from a invalid station ID
	 * 
	 * @see FireStationService#getAddressesFromStationID(Integer)
	 */
	@Test
	void testGetAddressesFromInvalidStationID() {

		// GIVEN
		repository.save(dataGenerator.generateTestStation());

		FireStation testStation2 = dataGenerator.generateTestStation();
		testStation2.setAddress("Y Test road");
		testStation2 = repository.save(testStation2);

		FireStation testStation3 = dataGenerator.generateTestStation();
		testStation3.setAddress("Z Test road");
		testStation3.setStationId(2);
		testStation3 = repository.save(testStation3);

		// WHEN
		ArrayList<String> resultAddresses = testedService.getAddressesFromStationID(3);

		// THEN
		if (resultAddresses != null && resultAddresses.size() > 0)
			fail("Expected array size was 0 but was " + resultAddresses.size());
	}

	/**
	 * Tests if the right station ID si returned by valid address
	 * 
	 * @see FireStationService#getStationIDFromAddress(String)
	 */
	@Test
	void testGetStationIDFromValidAddress() {

		// GIVEN
		Integer expectedStationID = 1;

		repository.save(dataGenerator.generateTestStation());

		FireStation testStation2 = dataGenerator.generateTestStation();
		testStation2.setStationId(2);
		testStation2 = repository.save(testStation2);

		FireStation testStation3 = dataGenerator.generateTestStation();
		testStation3.setStationId(3);
		testStation3 = repository.save(testStation3);

		// WHEN
		Integer resultStationID = testedService.getStationIDFromAddress("X Test road");

		// THEN
		assertEquals(expectedStationID, resultStationID);
	}

	/**
	 * Tests if the right station ID si returned by invalid address
	 * 
	 * @see FireStationService#getStationIDFromAddress(String)
	 */
	@Test
	void testGetStationIDFromInvalidAddress() {

		// GIVEN
		Integer expectedStationID = -1;

		repository.save(dataGenerator.generateTestStation());
		repository.save(dataGenerator.generateTestStation());
		repository.save(dataGenerator.generateTestStation());

		// WHEN
		Integer resultStationID = testedService.getStationIDFromAddress("Z Test road");

		// THEN
		assertEquals(expectedStationID, resultStationID);
	}
}
package com.safetynet.alerts.service;

import com.safetynet.alerts.repository.FireStationRepository;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the interactions between FireStation and the database
 * 
 * @see FireStation
 * @author MikeMatthews
 */
@SpringBootTest
public class FireStationServiceIT {

	@Autowired
	FireStationRepository repository;

	@Autowired
	FireStationService testedService;

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
		// WHEN
		// THEN
	}

	/**
	 * Tests if the provided FireStation object is updated in database
	 * 
	 * @see FireStationService#updateRecord(FireStation)
	 */
	@Test
	public void testStationUpdate() {

		// GIVEN
		// WHEN
		// THEN
	}

	/**
	 * Tests if the provided FireStation object is deleted in database
	 * 
	 * @see FireStationService#removeFireStation(Long)
	 */
	@Test
	public void testStationDelete() {

		// GIVEN
		// WHEN
		// THEN
	}
}
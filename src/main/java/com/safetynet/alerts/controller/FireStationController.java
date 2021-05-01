package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to receive requests regarding the FireStations
 * 
 * @see FireStationService
 * 
 * @author Mike Matthews
 */
@RestController
public class FireStationController {

	@Autowired
	FireStationService service;

	/**
	 * Method receiving Post requests
	 * 
	 * @return the FireStation object saved and updated
	 */
	@PostMapping("/firestation")
	public FireStation saveStation(FireStation station) {

		if (station != null)
			return service.addFireStation(station);
		else {
			throw new IllegalArgumentException("The provided object was null");
		}
	}

	/**
	 * Method receiving Put requests
	 * 
	 * @return true if the operation was a success
	 */
	@PutMapping("/firestation")
	public boolean updateStation(FireStation station) {

		if (station != null)
			return service.updateFireStation(station);
		else {
			throw new IllegalArgumentException("The provided object was null");
		}
	}

	/**
	 * Method receiving Delete requests
	 */
	@DeleteMapping("/firestation")
	public void deleteStation(final Long stationID) {

		if (stationID > -1)
			service.removeFireStation(stationID);
		else {
			throw new IllegalArgumentException("The provided index was negative");
		}
	}
}